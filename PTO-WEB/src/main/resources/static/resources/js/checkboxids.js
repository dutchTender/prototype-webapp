$(document).ready(function(){

//START initialize datable
		var table = $('#goodsandservices').DataTable({
			responsive: {
            details: true,
			},
			"autoWidth": false,
			"responsive": true,
			"columns": [
				{ "width": "12%" },
				{ "width": "25%" },
				{ "width": "30%" },
				{ "width": "33%" },
			  ],
		});
	//END initialize datable

//START set initial checkboxes
  //$('input[type=checkbox]').attr('checked',false);
  //END set initial checkboxes
  
  //START make label for datatable checkboxes   
	//$( '#goodsandservices_wrapper input[type=checkbox]' ).each(function() {
//		var x = $( 'input[type=checkbox]' ).index( this );
//		$( this ).attr('id', 'a' + x);
//	});
//	$( '#goodsandservices_wrapper label' ).each(function() {
//		var y = $( this ).siblings( 'input[type=checkbox]' ).attr('id');
//		$( this ).attr('for', y);
//	});
	//END make label for datatable checkboxes



});
