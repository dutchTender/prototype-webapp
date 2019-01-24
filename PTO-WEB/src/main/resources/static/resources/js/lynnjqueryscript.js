$(document).ready(function(){
	//START expand / collapse glyphicon
	$("button#optionsbtn").click(function() {
		$( 'button#optionsbtn span' ).toggleClass('visuallyremoved');
		$( 'button#optionsbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#translatesbtn").click(function() {
		$( 'button#translatesbtn span' ).toggleClass('visuallyremoved');
		$( 'button#translatesbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#colorbtn").click(function() {
		$( 'button#colorbtn span' ).toggleClass('visuallyremoved');
		$( 'button#colorbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#translitbtn").click(function() {
		$( 'button#translitbtn span' ).toggleClass('visuallyremoved');
		$( 'button#translitbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#npsbtn").click(function() {
		$( 'button#npsbtn span' ).toggleClass('visuallyremoved');
		$( 'button#npsbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#disclaimerbtn").click(function() {
		$( 'button#disclaimerbtn span' ).toggleClass('visuallyremoved');
		$( 'button#disclaimerbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#priorbtn").click(function() {
		$( 'button#priorbtn span' ).toggleClass('visuallyremoved');
		$( 'button#priorbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#meanbtn").click(function() {
		$( 'button#meanbtn span' ).toggleClass('visuallyremoved');
		$( 'button#meanbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#entbtn").click(function() {
		$( 'button#entbtn span' ).toggleClass('visuallyremoved');
		$( 'button#entbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//END expand / collapse glyphicon

	//END initialize datable
	//start close (x) gs panels
	$('.closegspanels').click(function() {
		$( this ).parent().parent().parent().fadeOut( 'fast','swing');
	});
	//end close (x) gs panels

	//START set initial checkboxes

	//END set initial checkboxes

	//START enable / disable checkboxes
	$('input#certcheck').change(function() {
		if(this.checked == true){
			$("input#tradeserv").prop({
				disabled: true,
			});
			$("input#collectcheck").prop({
				disabled: true,
			});
			$("input#collectmember").prop({
				disabled: true,
			});
			$('.form-check#tradeserv2 span.radio').addClass('special');
			$('.form-check#collect2 span.radio').addClass('special');
			$('.form-check#collectmemb span.radio').addClass('special');
			$('.form-check#tradeserv2 span.radio').attr('aria-disabled', 'true');
			$('.form-check#collect2 span.radio').attr('aria-disabled', 'true');
			$('.form-check#collectmemb span.radio').attr('aria-disabled', 'true');
		}
		else{
			$("input#tradeserv").prop({
				disabled: false,
			});
			$("input#collectcheck").prop({
				disabled: false,
			});
			$("input#collectmember").prop({
				disabled: false,
			});
			$('.form-check#tradeserv2 span.radio').removeClass('special');
			$('.form-check#collect2 span.radio').removeClass('special');
			$('.form-check#collectmemb span.radio').removeClass('special');
			$('.form-check#tradeserv2 span.radio').attr('aria-disabled', 'false');
			$('.form-check#collect2 span.radio').attr('aria-disabled', 'false');
			$('.form-check#collectmemb span.radio').attr('aria-disabled', 'false');
		}
		$("input#certcheck").on( "click", function(){
			$(".form-check#tradeserv2 span.radio").addClass("special");
			$(".form-check#collect2 span.radio").addClass("special");
			$(".form-check#collectmemb span.radio").addClass("special");
			$(".form-check#tradeserv2 span.radio").attr('aria-disabled', 'true');
			$(".form-check#collect2 span.radio").attr('aria-disabled', 'true');
			$(".form-check#collectmemb span.radio").attr('aria-disabled', 'true');
		});
	});
	//When 'Collective' or 'Collective Membership' are checked, 'Trademark / Servicemark' and 'Certification' are disabled
	$('input#collectcheck,input#collectmember').change(function() {
		if(this.checked == true){
			$('input#certcheck').prop({
				disabled: true,
			});
			$('.form-check#cert span.radio').addClass('special');
			$('.form-check#cert span.radio').attr('aria-disabled', 'true');
		}
		else{
			$('input#certcheck').prop({
				disabled: false,
			});
			$('.form-check#cert span.radio').removeClass('special');
			$('.form-check#cert span.radio').attr('aria-disabled', 'false');
		}
	});
	//When 'Trademark / Servicemark' is checked, 'Certification' is disabled
	$('input#tradeserv').change(function(e) {
		if(this.checked == true){
			$('input#certcheck').prop({
				disabled: true,
			});
			$('.form-check#cert span.radio').addClass('special');
			$('.form-check#cert span.radio').attr('aria-disabled', 'true');
		}
		else{
			$('input#certcheck').prop({
				disabled: false,
			});
			$('.form-check#cert span.radio').removeClass('special');
		}
	});//END enable / disable checkboxes

	//START toggle panel color
	$( '.nocontent' ).click(function() {
		$( document ).find('div').removeClass( 'highlight focus' );
		$( this ).addClass( 'highlight focus' );
	});
	//END toggle panel color

	//START panel height match
	var a = $( 'div.match' );
	$('#panels .panel-body').css( 'height', (a.innerHeight() + 8) );
	//END

	//START close button height match
	var d = $( '.closepans' ).prev('div');
	$(d).css('display','flex').css('flex-direction','column');
	$('#gsselected .closepans').css( 'height', (d.innerHeight()) );
	$('#gsselected .closegspanels').css('line-height',(d.innerHeight() + 'px'));
	$( window ).resize(function() {
		$('#gsselected .closepans').css( 'height', (d.innerHeight()) );
		$('#gsselected .closegspanels').css('line-height',(d.innerHeight() + 'px'));
	});
	//END close button height match

	//START input label height match
	var d = $( '.matchlabelheight' ).parent().prev('div').children('label');
	var e = $( '.matchlabelheighttwo' ).parent().prev('div').children('label');
	$('.matchlabelheight').css( 'height', (d.innerHeight() + 'px') );
	$('.matchlabelheighttwo').css( 'height', (e.innerHeight() + 'px') );
	$( window ).resize(function() {
		$('.matchlabelheight').css( 'height', (d.innerHeight())  + 'px' );
		$('.matchlabelheighttwo').css( 'height', (e.innerHeight())  + 'px' );
	});
	//END input label height match

	//START examples panel link hover / active
	$('#examples div.panel a').hover(function() {
		$(this).css('display','block');
		$( this ).parent().parent().parent().addClass( 'examplehover' );
	}, function() {
		$( this ).parent().parent().parent().removeClass( 'examplehover' );
	});
	//END examples panel link hover / active

	//START examples panel button toggle text
	$( 'button#moreoptionsbtn' ).click(function() {
		$( this ).toggleClass( 'focus' );
		$(this).text() === 'See More Types'
			? $(this).text('See Less Types')
			: $(this).text('See More Types');
	});
	//END examples panel button toggle text

	//Initialize tooltip
	$("body").tooltip({
		selector: "[data-toggle='tooltip']",
		container: "body"
	});
	//Initialize popover

	//Display uploaded filename
	$('input[type="file"]').change(function(e){
		var fileName = e.target.files[0].name;
		$('a.list-group-item').html('<span class="filename">' + fileName + '</span>' + '<span class="badge alert-success pull-right">Success</span>')
	});

	//+ Translation Item
	//

	//- Translation Item
	//

	//START toggle radio buttons content
	//start mark color options

	$('input#inlineRadio1').change(function() {
		if(this.checked == true){
			$('div#nocolorclaim').hide( 500 );
			$('div#yescolorclaim').show( 500 );
		}
		else {
			$('div#nocolorclaim').show( 500 );
			$('div#yescolorclaim').hide( 500 );
		}
	});
	$('input#inlineRadio2').change(function() {
		if(this.checked == true){
			$('div#nocolorclaim').show( 500 );
			$('div#yescolorclaim').hide( 500 );
		}
		else {
			$('div#nocolorclaim').hide( 500 );
			$('div#yescolorclaim').show( 500 );
		}
	});
	//end mark color options


	$('input#inlineRadio3').change(function() {
		if(this.checked == true){
			$('div#yestranslation').show( 500 );
		}
		else {
			$('div#yestranslation').hide( 500 );
		}
	});
	$('input#inlineRadio4').change(function() {
		if(this.checked == true){
			$('div#yestranslation').hide( 500 );
		}
		else {
			$('div#yestranslation').show( 500 );
		}
	});
	//end translations options


	$('input#inlineRadio5').change(function() {
		if(this.checked == true){
			$('div#yestransliteration').show( 500 );
		}
		else {
			$('div#yestransliteration').hide( 500 );
		}
	});
	$('input#inlineRadio6').change(function() {
		if(this.checked == true){
			$('div#yestransliteration').hide(500 );
		}
		else {
			$('div#yestransliteration').show( 500 );
		}
	});
	//end translations options


	$('input#inlineRadio7').change(function() {
		if(this.checked == true){
			$('div#yesnps').show( 500 );
		}
		else {
			$('div#yesnps').hide( 500 );
		}
	});
	$('input#inlineRadio8').change(function() {
		if(this.checked == true){
			$('div#yesnps').hide( 500 );
		}
		else {
			$('div#yesnps').show( 500 );
		}
	});
	//yes, contains name is checked
	$('div#yescontainsname').hide();
	$('#namechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainsname').show( 500 );
		}
		else {
			$('div#yescontainsname').hide( 500 );
		}
	});
	//yes, contains portrait is checked
	$('div#yescontainsportrait').hide();
	$('#portraitchecked').change(function() {
		if(this.checked == true){
			$('div#yescontainsportrait').show( 500 );
		}
		else {
			$('div#yescontainsportrait').hide( 500 );
		}
	});
	//yes, contains signature is checked
	$("#signaturechecked").prop({
		checked: false,
	});
	$('div#yescontainssignature').hide();
	$('#signaturechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainssignature').show( 500 );
		}
		else {
			$('div#yescontainssignature').hide( 500 );
		}
	});
	//end nps options


	$('input#inlineRadio09').change(function() {
		if(this.checked == true){
			$('div#yesdisclaimer').show( 500 );
		}
		else {
			$('div#yesdisclaimer').hide( 500 );
		}
	});
	$('input#inlineRadio010').change(function() {
		if(this.checked == true){
			$('div#yesdisclaimer').hide( 500 );
		}
		else {
			$('div#yesdisclaimer').show( 500 );
		}
	});
	//end disclaimer options


	$('div#yesprior').css('display','none');
	$('input#inlineRadio011').change(function() {
		if(this.checked == true){
			$('div#yesprior').show( 500 );
		}
		else {
			$('div#yesprior').hide( 500 );
		}
	});
	$('input#inlineRadio012').change(function() {
		if(this.checked == true){
			$('div#yesprior').hide( 500 );
		}
		else {
			$('div#yesprior').show( 500 );
		}
	});
	//end prior options

	$('input#inlineRadio013').change(function() {
		if(this.checked == true){
			$('div#yesmeaning').show( 500 );
		}
		else {
			$('div#yesmeaning').hide( 500 );
		}
	});
	$('input#inlineRadio014').change(function() {
		if(this.checked == true){
			$('div#yesmeaning').hide( 500 );
		}
		else {
			$('div#yesmeaning').show( 500 );
		}
	});
	//end prior options


	$('div#yesattorneyfiling').css('display','none');
	$('input#inlineRadio015').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').show( 500 );
		}
		else {
			$('div#yesattorneyfiling').hide( 500 );
		}
	});
	$('input#inlineRadio016').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').hide( 500 );
		}
		else {
			$('div#yesattorneyfiling').show( 500 );
		}
	});
	//end attorney options


	$('div#yesusentity').css('display','none');
	$('input#inlineRadio019').change(function() {
		if(this.checked == true){
			$('div#yesusentity').show( 500 );

			$("input#inlineRadio020").prop({
				checked: false,
			});
		}
		else {
			$('div#yesusentity').hide( 500 );

			$("input#inlineRadio020").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio020').change(function() {
		if(this.checked == true){
			$('div#yesusentity').hide(500 );

			$("input#inlineRadio019").prop({
				checked: false,
			});
		}
		else {
			$('div#yesusentity').show( 500 );

			$("input#inlineRadio019").prop({
				checked: true,
			});
		}
	});
	//end us foreign options



	$('input#inlineRadio25').change(function() {
		if(this.checked == true){
			$('div#yescommerce').show( 500 );
		}
		else {
			$('div#yescommerce').hide( 500 );
		}
	});
	$('input#inlineRadio26').change(function() {
		if(this.checked == true){
			$('div#yescommerce').hide( 500 );
		}
		else {
			$('div#yescommerce').show( 500 );
		}
	});
	//end basis commerce options


	$('div#yesconnection').css('display','none');
	$('input#inlineRadio29').change(function() {
		if(this.checked == true){
			$('div#yesconnection').show( 500);
		}
		else {
			$('div#yesconnection').hide( 500 );
		}
	});
	$('input#inlineRadio30').change(function() {
		if(this.checked == true){
			$('div#yesconnection').hide( 500 );
		}
		else {
			$('div#yesconnection').show( 500 );
		}
	});
	//end basistwo connection options

	//END toggle radio buttons content

	//start affiliation options
	$('div#usaffiliation').css('display','none');
	$('div#canadianaffiliation').css('display','none');
	$('#attorney-bar-standing').change(function(){
		$('.hidethis').hide( 500 );
		$('#' + $(this).val()).show( 500 );
	});
	//end affiliation options
	//start nameoftypeofbusiness options
	$( 'div#nametype' ).css('display','none');
	$( '#type' ).on('change',function(){
		$( 'div#nametype' ).show( 500 );
	});
	//start entity options (import concept)
	$('#autofill').css('display','none');
	$('#autofillForeign').css('display','none');
	$('#entype').on('change',function(){
		$('#container').empty();
		$('footer').css('display','none');
		//var include = ('js/' + $(this).val() + '.js');
		//$.getScript( include );
		$('#autofill').show( 500 );
		$('footer').css('display','block');
	});
	//end entity options (import concept)


	//END fill from contacts values -- attorney

	//START contacts, fees, my mark components
	//start toggle glyphicon contacts widget
	$('a.fromcontact').click(function() {
		$( document ).find('span.glyphicon-ok-sign.visuallyadded').addClass( 'visuallyremoved' ).removeClass( 'visuallyadded' ).parent().parent().css('background-color','#9BB8D3').siblings().css('background-color','#9BB8D3');
		$( document ).find('span.glyphicon-plus-sign.visuallyremoved').addClass( 'visuallyadded' ).removeClass( 'visuallyremoved' );
		$( this ).find('span.glyphicon-plus-sign').addClass('visuallyremoved').removeClass( 'visuallyadded' );
		$( this ).find('span.glyphicon-ok-sign').addClass('visuallyadded').removeClass('visuallyremoved').parent().parent().css('background-color','#D4EB8E').siblings().css('background-color','#D4EB8E');
	});
	//end toggle glyphicon contacts widget

	//start close (x) fees
	$('button#closefee').click(function() {
		$( '#mydata' ).css('visibility','hidden');
		$( '#mydata .collapse' ).collapse('hide').fadeOut( 'slow','swing');
		$( '#mydata button#feebtn span' ).toggleClass('visuallyremoved');
		$( '#mydata button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//end close (x) fees

	//start show fees from nav
	$('a#showfees').click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( '#mydata button#feebtn span' ).toggleClass('visuallyremoved');
		$( '#mydata button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//end show fees from nav

	//start show fees from widget
	$("button#feebtn").click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#feebtn span' ).toggleClass('visuallyremoved');
		$( 'button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//end show fees from widget

	//start close (x) managed contacts
	$('button#closecontacts').click(function() {
		$( 'button#contactsbtn span#toggleglyphone' ).toggleClass('visuallyremoved','visuallyadded');
		$( 'button#contactsbtn span#toggleglyph' ).toggleClass('visuallyadded','visuallyremoved');
		$( '#mydata2 .collapse' ).collapse('hide').fadeOut( 'slow','swing');
		$( '#mydata2' ).css('visibility','hidden');
	});
	//end close (x) managed contacts

	//start show managed contacts from widget
	$("button#contactsbtn").click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#contactsbtn span#toggleglyphone' ).toggleClass('visuallyremoved','visuallyadded');
		$( 'button#contactsbtn span#toggleglyph' ).toggleClass('visuallyadded','visuallyremoved');
	});
	//end show managed contacts from widget

	//start show managed contacts from nav
	$('a#showcontacts').click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
	});
	//populate form from managed contacts
	$('button#autofill').on('click',function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#contactsbtn span#toggleglyphone' ).addClass('visuallyremoved');
		$( 'button#contactsbtn span#toggleglyph' ).addClass('visuallyadded');
	});

	$('button#autofillForeign').on('click',function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#contactsbtn span#toggleglyphone' ).addClass('visuallyremoved');
		$( 'button#contactsbtn span#toggleglyph' ).addClass('visuallyadded');
	});
	//end show managed contacts from nav

	//START additional phone
	$( 'button#addphone' ).on('click',function(){
		$( '.phones:eq(0)' ).clone().appendTo( '.appendphone' );
	});
	$( '#resetphone' ).click(function () {
		$( '.appendphone .phones' ).remove( '.phones:eq(0)' );
	});
	//END additional phone

	//START additional docket
	$( 'button#addocket' ).click(function(){
		$( '#copy:eq(0)' ).clone().appendTo( '.appenddocket' );
	});
	$("#resetdockets").click(function () {
		$( '.appenddocket #copy' ).remove('#copy:eq(0)');
	});
	//END additional docket

	//START additional containsname
	$( 'button#addaname' ).on('click',function(){
		$( '.containsaname:eq(0)' ).clone().appendTo( '.appendaname' );
	});
	$( '#resetaname' ).on('click',function () {
		$( '.appendaname .containsaname' ).remove( '.containsaname:eq(0)' );
	});
	//END additional containsname



	$( 'button#addaname' ).on('click',function(){
		$( '.containsaname:eq(0)' ).clone().appendTo( '.appendaname' );
	});
	$( '#resetaname' ).on('click',function () {
		$( '.appendaname .containsaname' ).remove( '.containsaname:eq(0)' );
	});
	//END additional pending

	//START additional containsportrait
	$( 'button#addaportrait' ).on('click',function(){
		$( '.containsaportrait:eq(0)' ).clone().appendTo( '.appendaportrait' );
	});
	$( '#resetaportrait' ).on('click',function () {
		$( '.appendaportrait .containsaportrait' ).remove( '.containsaportrait:eq(0)' );
	});
	//END additional containsportrait

	//START additional containssignature
	$( 'button#addasignature' ).on('click',function(){
		$( '.containsasignature:eq(0)' ).clone().appendTo( '.appendasignature' );
	});
	$( '#resetasignature' ).on('click',function () {
		$( '.appendasignature .containsasignature' ).remove( '.containsasignature:eq(0)' );
	});
	//END additional containssignaturet

	//START additional foreignreg
	$( 'button#addforeign' ).on('click',function(){
		$( '.holdsaforeign:eq(0)' ).clone().appendTo( '.appendaforeign' );
	});
	$( '#resetforeign' ).on('click',function () {
		$( '.appendaforeign .holdsaforeign' ).remove( '.holdsaforeign:eq(0)' );
	});
	//END additional foreignreg

	//START additional foreignpending
	$( 'button#addpending' ).on('click',function(){
		$( '.holdsapending:eq(0)' ).clone().appendTo( '.appendapending' );
	});
	$( '#resetpending' ).on('click',function () {
		$( '.appendapending .holdsapending' ).remove( '.holdsapending:eq(0)' );
	});
	//END additional foreignpending


	//START show standard character preview
	$( '#ta2' ).keyup(function(){
		var currentText = $(this).val();
		$( 'p#showmarktxt' ).text(currentText);
	});


	//foreign entity show
	$('div#yesforeignentity').css('display','none');
	$('input#inlineRadio020').change(function() {
		if(this.checked == true){
			$('div#yesforeignentity').show( 500);

			$("input#inlineRadio019").prop({
				checked: false,
			});
		}
		else {
			$('div#yesforeignentity').hide( 500 );

			$("input#inlineRadio019").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio019').change(function() {
		if(this.checked == true){
			$('div#yesforeignentity').hide( 500 );

			$("input#inlineRadio020").prop({
				checked: false,
			});
		}
		else {
			$('div#yesforeignentity').show( 500 );

			$("input#inlineRadio020").prop({
				checked: true,
			});
		}
	});
	//foreign select by country
	$('.albaniaform, .algeriaform, .angolaform, .bahamasform').css('display','none');
	$('#entitycountry').on('change',function(){
		var loadselectmenu = ('.' + $(this).val() + 'form');
		$('.hidethis').hide('fast');
		$( loadselectmenu ).show('slow').addClass('hidethis');
		$( loadselectmenu ).css('display','block');
	});

	//START additional info checkboxes
	$('div#tr').css('display','none');
	$('input#typeregi').change(function() {
		if(this.checked == true){
			$('#tr').show( 'fast' );
		}
		else {
			$('#tr').hide( 'fast' );
		}
	});
	$('div#cad').css('display','none');
	$('input#distinct').change(function() {
		if(this.checked == true){
			$('#cad').show( 'fast' );
		}
		else {
			$('#cad').hide( 'fast' );
		}
	});
	$('div#uaf').css('display','none');
	$('input#useanotherform').change(function() {
		if(this.checked == true){
			$('#uaf').show( 'fast' );
		}
		else {
			$('#uaf').hide( 'fast' );
		}
	});
	$('div#cc').css('display','none');
	$('input#concurruse').change(function() {
		if(this.checked == true){
			$('#cc').show( 'fast' );
		}
		else {
			$('#cc').hide( 'fast' );
		}
	});
	//END additional info checkboxes

	//start distinctive options
	$('div#yesdistict').css('display','none');
	$('input#inlineRadio033').change(function() {
		if(this.checked == true){
			$('div#yesdistict').show( 'fast' );
		}
		else {
			$('div#yesdistict').hide( 'fast' );
		}
	});
	$('input#inlineRadio034').change(function() {
		if(this.checked == true){
			$('div#yesdistict').hide( 'fast' );
		}
		else {
			$('div#yesdistict').show( 'fast' );
		}
	});
	//other form
	$('div#yesotherform').css('display','none');
	$('input#inlineRadio040').change(function() {
		if(this.checked == true){
			$('div#yesotherform').show( 'fast' );
		}
		else {
			$('div#yesotherform').hide( 'fast' );
		}
	});
	$('input#inlineRadio041').change(function() {
		if(this.checked == true){
			$('div#yesotherform').hide( 'fast' );
		}
		else {
			$('div#yesotherform').show( 'fast' );
		}
	});
	//whole or in part
	$('div#yeswhole').css('display','none');
	$('div#yespart').css('display','none');
	$('input#inlineRadio035').change(function() {
		if(this.checked == true){
			$('div#yeswhole').show( 'fast' );
			$('div#yespart').hide( 'fast' );
		}
		else {
			$('div#yeswhole').hide( 'fast' );
		}
	});
	$('input#inlineRadio036').change(function() {
		if(this.checked == true){
			$('div#yeswhole').show( 'fast' );
			$('div#yespart').show( 'fast' );
		}
		else {
			$('div#yespart').hide( 'fast' );
		}
	});
	//whole or in part (other form)
	$('div#yeswholeother').css('display','none');
	$('div#yespartother').css('display','none');
	$('input#inlineRadio042').change(function() {
		if(this.checked == true){
			$('div#yeswholeother').show( 'fast' );
			//$(heightmatchbackwards);
			$('div#yespartother').hide( 'fast' );
		}
		else {
			$('div#yeswholeother').hide( 'fast' );
		}
	});
	$('input#inlineRadio043').change(function() {
		if(this.checked == true){
			$('div#yeswholeother').show( 'fast' );
			$('div#yespartother').show( 'fast' );
		}
		else {
			$('div#yespartother').hide( 'fast' );
		}
	});
	//evidence
	$('div#yesevidence').css('display','none');
	$('div#yespriors').css('display','none');
	$('input#inlineRadio037').change(function() {
		if(this.checked == true){
			$('div#yesevidence').show( 'fast' );
			$('div#yespriors').hide( 'fast' );
		}
		else {
			$('div#yesevidence').hide( 'fast' );
		}
	});
	$('input#inlineRadio038').change(function() {
		if(this.checked == true){
			$('div#yesevidence').hide( 'fast' );
			$('div#yespriors').show( 'fast' );
		}
		else {
			$('div#yesevidence').show( 'fast' );
			$('div#yespriors').hide( 'fast' );
		}
	});
	$('input#inlineRadio039').change(function() {
		if(this.checked == true){
			$('div#yesevidence').hide( 'fast' );
			$('div#yespriors').hide( 'fast' );
		}
		else {
			$('div#yesevidence').show( 'fast' );
			$('div#yespriors').show( 'fast' );
		}
	});
	//hide / show concurrent uses
	$('div#hideshowconcurrentuses').css('display','none');
	$('input#courtd, input#ttabmade, input#conflict, input#earlieruse').change(function() {
		if(this.checked == true){
			$('div#hideshowconcurrentuses').show( 'fast' );
		}
		else if ($('input#courtd').prop('checked')){
			$('div#hideshowconcurrentuses').show('fast');
		}
		else if ($('input#ttabmade').prop('checked')){
			$('div#hideshowconcurrentuses').show('fast');
		}
		else if ($('input#conflict').prop('checked')){
			$('div#hideshowconcurrentuses').show('fast');
		}
		else if ($('input#earlieruse').prop('checked')){
			$('div#hideshowconcurrentuses').show('fast');
		}
		else {
			$('div#hideshowconcurrentuses').hide( 'fast' );
		}
	});

	//select signature method

	$('#signmethod').on('change',function(){
		var loadsign = ('#' + $(this).val());
		$('.hidethis').hide('fast');
		$( loadsign ).show('fast').addClass('hidethis');
		$( loadsign ).css('display','block');
		//console.log(loadsign);
	});
	//END signature method


});
