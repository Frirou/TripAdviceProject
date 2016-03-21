$(function() {
	$('.field, textarea').focus(function() {
        if(this.title==this.value) {
            this.value = '';
        }
    }).blur(function(){
        if(this.value=='') {
            this.value = this.title;
        }
    });

    $("#slider").jcarousel({
        scroll: 1,
        auto: 3,
        wrap: 'both',
        vertical: true,
        initCallback: mycarousel_initCallback,        
        buttonNextHTML: null,
        buttonPrevHTML: null,
        itemVisibleInCallback: {
            onAfterAnimation: function(c, o, i, s) {
                $('.slider-nav li').removeClass('active');
                $('.slider-nav li:eq('+ (i-1) +')').addClass('active');
            }
        }        
    });
    
 
    if ($.browser.msie && $.browser.version == 6) {
        DD_belatedPNG.fix('#navigation a, #slider-frame, .caption');
    }

});

function mycarousel_initCallback(carousel) {
    $('.slider-nav ul li').bind('click', function() {      
    	$('.slider-nav ul li').removeClass('active');
    	$(this).addClass('active');	     		      
        carousel.scroll(jQuery.jcarousel.intval(jQuery(this).text()));    
        return false;
    });
} 