(function($, window, document) {
	
	$(document).ready(function() {
		initApp();
	});
	
	EDGE_LENGTH = 50;
	
	function initApp() {
		drawMap(EDGE_LENGTH);
		paintStartingStructure();
        setupResetButton();
		setupStartButton();
        setupNextButton();
        toggleRules();
        localStorage.setItem("on_load_counter", '0');
	}
	
	function drawMap(edgeLength) {
		var mainContainer = $('div#main');
		for (i = 0; i < edgeLength; i++) {
			for (j = 0; j < edgeLength; j++) {
				mainContainer.append('<div class="cell' + additionalClasses(i, j) + '" x=' + j + ' y=' + i + ' />');
			}
			mainContainer.append('<div class="clear" />');
		}
		$('div.cell').bind('click', function() {
			$(this).toggleClass('black');
		});
	}
	
	function additionalClasses(topEdge, leftEdge) {
		var classes = '';
		if (0 === topEdge) {
			classes += ' top-cell';
		}
		if (0 === leftEdge) {
			classes += ' left-cell';
		}
		return classes;
	}
	
	function paintStartingStructure() {
		paint(17, 17);
		paint(17, 18);
		paint(16, 18);
		paint(16, 19);
		paint(18, 18);
		paint(18, 19);
		paint(17, 20);
	}
	
	function paint(x, y) {
		$('.cell[x=' + x + '][y=' + y + ']').addClass('black');
	}
	
	function getJsonFromPaintedElements() {
		var jsonObject = {};
		jsonObject.edgeLength = EDGE_LENGTH;
		jsonObject.elements = [];
		
		var elements = $('.cell.black');
		$.each(elements, function(index, element) {
			var e = {};
			e.x = parseInt($(element).attr('x'));
			e.y = parseInt($(element).attr('y'));
			jsonObject.elements.push(e);
		});
		return JSON.stringify(jsonObject);
	}
    //This function is triggered on reset Button Click
    function toggleRules() {
        $('div#toggleRules').bind('click', function() {
            $('div#rules').toggle();
        });
    }
    //This function is triggered on reset Button Click
    function setupResetButton() {
        $('input#reset-button').bind('click', function() {
            location.reload(true);
        })
    }
	//This function is triggered on Auto Generate Button Click
	function setupStartButton() {
		$('input#start-button').bind('click', function() {
            generateGameOfLife();
		})
	}
    //This function is triggered on Next Generation Button Click
    function setupNextButton() {
        $('input#next-button').bind('click', function() {
            sendNextRequest();
        })
    }
    // Generate the initial state and following generations
	function generateGameOfLife() {
		var paintedElements = getJsonFromPaintedElements();
		$.ajax({
			url : '/rest/lifegame/calculate',
			type : 'post',
			data : paintedElements,
			contentType: 'application/json; charset=utf-8',
			dataType : 'json',
			async : false,
			success : function(recalculatedStructure) {
				console.log("recalculatedStructure: " + recalculatedStructure, recalculatedStructure);
                var n = localStorage.getItem('on_load_counter');
                if (n === null) {
                    n = 0;
                }
                n++;
                localStorage.setItem("on_load_counter", n);
				repaintStructure(recalculatedStructure);
				setTimeout(generateGameOfLife, 250);
			}
		});			
	}

	// Calculate the next generation based on current generation
    function sendNextRequest() {
        var paintedElements = getJsonFromPaintedElements();
        $.ajax({
            url : '/rest/lifegame/calculate',
            type : 'post',
            data : paintedElements,
            contentType: 'application/json; charset=utf-8',
            dataType : 'json',
            async : false,
            success : function(recalculatedStructure) {
                console.log("recalculatedStructure: " + recalculatedStructure, recalculatedStructure);
                console.log("recalculatedStructure SIZE: " + recalculatedStructure.elements.length);
                repaintStructure(recalculatedStructure);
                var n = localStorage.getItem('on_load_counter');
                if (n === null) {
                    n = 0;
                }
                n++;
                localStorage.setItem("on_load_counter", n);
                document.getElementById('generationId').innerHTML = n;
            }
        });
    }
	function repaintStructure(structure) {
		clearMap();
		paintStructure(structure);
	}
	
	function paintStructure(structure) {
		$.each(structure.elements, function(index, element) {
			paint(element.x, element.y);
		});
	}
	
	function clearMap() {
		$('.cell.black').removeClass('black');
	}
	
}(window.jQuery, window, document));