$(document).ready(function(){
  //START expand / collapse link button
  $("#markoptions").on("hide.bs.collapse", function(){
    $(".btn").html('<span class="glyphicon glyphicon-triangle-right"></span> Uncommon Mark Options');
  });
  $("#markoptions").on("show.bs.collapse", function(){
    $(".btn").html('<span class="glyphicon glyphicon-triangle-bottom"></span> Uncommon Mark Options');
  });
  //END expand / collapse link button
  
  //START set initial checkboxes
  $('input[type=checkbox]').attr('checked',false);
  //END set initial checkboxes

  //START enable / disable checkboxes
  //When 'Certification' is checked, 'Trademark / Servicemark', 'Collective' and 'Collective Membership' are disabled
	$('input#certcheck').change(function() {
		if(this.checked == true){
			$("input#tradeserv").prop({
				disabled: true,				
			});
			$("input#collectcheck").prop({
				disabled: true,				
			});
			$("input#collectmember").prop({
				disabled: true
			});
			$('.form-check#tradeserv2 span.radio').toggleClass('special');
			$('.form-check#collect2 span.radio').toggleClass('special');
			$('.form-check#collectmemb span.radio').toggleClass('special');
		}
		else{
			$("input#tradeserv").prop({
				disabled: false
			});
			$("input#collectcheck").prop({
				disabled: false
			});
			$("input#collectmember").prop({
				disabled: false
			});
		}
		$("input#certcheck").click(function(){
			$(".form-check#tradeserv2 span.radio").toggleClass("special");
			$(".form-check#collect2 span.radio").toggleClass("special");
			$(".form-check#collectmemb span.radio").toggleClass("special");
		});
	});
	//When 'Collective' or 'Collective Membership' are checked, 'Trademark / Servicemark' and 'Certification' are disabled
	$('input#collectcheck,input#collectmember').change(function() {
		if(this.checked == true){
			$('input#tradeserv').prop({
				disabled: true,				
			});
			$('input#certcheck').prop({
				disabled: true,				
			});
			$('.form-check#tradeserv2 span.radio').toggleClass('special');
			$('.form-check#cert span.radio').toggleClass('special');
		}
		else{
			$('input#tradeserv').prop({
				disabled: false
			})
			$('input#certcheck').prop({
				disabled: false
			});
		}
		$('input#collectcheck,input#collectmember').click(function(){
			$('.form-check#tradeserv2 span.radio').toggleClass('special');
			$('.form-check#cert span.radio').toggleClass('special');
		});
	});
	//When 'Trademark / Servicemark' is checked, 'Certification' is disabled
		$('input#tradeserv').change(function(e) {
			if(this.checked == true){
				$('input#certcheck').prop({
					disabled: true,				
				});
				$('.form-check#cert span.radio').toggleClass('special');
			}
			else{
				$('input#certcheck').prop({
					disabled: false
				});
			}
		$('input#tradeserv').click(function(){
			$('.form-check#cert span.radio').toggleClass('special');
		});
	});//END enable / disable checkboxes  
	 
});
