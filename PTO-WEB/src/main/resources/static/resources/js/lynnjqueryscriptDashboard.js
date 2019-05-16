$(document).ready(function(){
    //START footer and login positioning
    $( window ).load(function() {
        var e = $( window ).height();
        var enav = $('.navbar-fixed-top').height();
        var efoot = $('footer').height();
        var winwidth = $(window).width();
        //if (winwidth > 768) {
        $('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
        $('main#loginform').css('position','relative').css('top',((e - efoot) - enav) / 6);
        //}
//		else if (winwidth < 767) {
//			$('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
//			$('main#loginform').css('position','relative').css('top', '0');
//			}
    });
    $( window ).resize(function() {
        var e = $( window ).height();
        var enav = $('.navbar-fixed-top').height();
        var efoot = $('footer').height();
        var winwidth = $(window).width();
        //if (winwidth > 768) {
        $('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
        $('main#loginform').css('position','relative').css('top',((e - efoot) - enav) / 6);
        //}
//		else if (winwidth < 767) {
//			$('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
//			$('main#loginform').css('position','relative').css('top', '0');
//			}
    });
    //END footer and login positioning
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
    $("button#marktypebtn").click(function() {
        $( 'button#marktypebtn span' ).toggleClass('visuallyremoved');
        $( 'button#marktypebtn span#toggleglyph' ).toggleClass('visuallyadded');
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
    $("button#registerbtn").click(function() {
        $( 'button#registerbtn span' ).toggleClass('visuallyremoved');
        $( 'button#registerbtn span#toggleglyph' ).toggleClass('visuallyadded');
    });
    $("button#distinctivenessbtn").click(function() {
        $( 'button#distinctivenessbtn span' ).toggleClass('visuallyremoved');
        $( 'button#distinctivenessbtn span#toggleglyph' ).toggleClass('visuallyadded');
    });
    //END expand / collapse glyphicon

    //START initialize Goods + Services datable
    var table = $('#goodsandservices').DataTable({
        "fnDrawCallback": function( oSettings ) {
            $( '#goodsandservices input[type=checkbox]' ).each(function() {
                var x = $( 'input[type=checkbox]' ).index( this );
                $( this ).attr('id', 'a' + x);
            });
            $( '#goodsandservices label' ).each(function() {
                var y = $( this ).siblings( 'input[type=checkbox]' ).attr('id');
                $( this ).attr('for', y);
            });
        },
        "autoWidth": false,
        "responsive": true,
        "columns": [
            { "width": "25%" },
            { "width": "25%" },
            { "width": "25%" },
            { "width": "25%" },
        ],
        "columnDefs": [
            { className: "centertxt", "targets": [ 0 ] }
        ]
    });
    //END initialize Goods + Services datable

    //START initialize Dashboard datable one
    var tableone = $('#dashboardtableone').DataTable({
        "fnDrawCallback": function( oSettings ) {
        },
        'sDom': '<"toolbar">lfrtip',
        "language": {
            "search": "<span class='glyphicon glyphicon-search' aria-hidden='true'></span><span class='sr-only'>search</span>",
            "lengthMenu": "<span class='glyphicon glyphicon-filter' aria-hidden='true'></span><span class='sr-only'>select number of entries to display</span> <select>"+
                '<option value="10">10</option>'+
                '<option value="25">25</option>'+
                '<option value="50">50</option>'+
                '<option value="100">100</option>'+
                '<option value="-1">All</option>'+
                '</select>'
        },
        'autoWidth': false,
        'responsive': true,
        'columns': [
            { 'width': '20%' },
            { 'width': '20%' },
            { 'width': '20%' },
            { 'width': '20%' },
            { 'width': '20%' },
        ],
        'columnDefs': [
            { responsivePriority: 1, targets: 0 },
            { responsivePriority: 2, targets: 1 },
            { responsivePriority: 3, targets: 3 },
            { responsivePriority: 4, targets: 2 },
            { responsivePriority: 5, targets: 4 },
            { className: 'centertxt', 'targets': [ 0,1,2,3,4 ] },
        ],
    });
    //END initialize Dashboard datable one

    //START initialize Dashboard datable two
    var tabletwo = $('#dashboardtabletwo').DataTable({
        "fnDrawCallback": function( oSettings ) {
        },
        'sDom': '<"toolbartwo">lfrtip',
        "language": {
            "search": "<span class='glyphicon glyphicon-search' aria-hidden='true'></span><span class='sr-only'>search</span>",
            "lengthMenu": "<span class='glyphicon glyphicon-filter' aria-hidden='true'></span><span class='sr-only'>select number of entries to display</span> <select>"+
                '<option value="10">10</option>'+
                '<option value="25">25</option>'+
                '<option value="50">50</option>'+
                '<option value="100">100</option>'+
                '<option value="-1">All</option>'+
                '</select>'
        },
        'autoWidth': false,
        'responsive': true,
        'columns': [
            { 'width': '12%' },
            { 'width': '17%' },
            { 'width': '14%' },
            { 'width': '14%' },
            { 'width': '15%' },
            { 'width': '14%' },
            { 'width': '14%' },
        ],
        'columnDefs': [
            { responsivePriority: 1, targets: 0 },
            { responsivePriority: 2, targets: 6 },
            { responsivePriority: 3, targets: 4 },
            { responsivePriority: 4, targets: 5 },
            { responsivePriority: 5, targets: 1 },
            { responsivePriority: 6, targets: 3 },
            { responsivePriority: 7, targets: 2 },
            { className: 'centertxt', 'targets': [ 0,1,2,3,4,5,6 ] },
        ],
    });
    //END initialize Dashboard datable two

    //Dashboard datatables ellipsis menu tableone
    $("div.toolbar").html('<div class="dropdown"><button class="btn btn-xs dropdown-toggle" type="button" id="dropdownMenucolvis" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-option-vertical"></span></button><ul class="dropdown-menu" aria-labelledby="dropdownMenucolvis"><li class="dropdown-header">Toggle columns</li><li><a class="toggle-vis" data-column="0">Serial#</a></li><li><a class="toggle-vis" data-column="1">Registration#</a></li><li><a class="toggle-vis" data-column="2">Owner</a></li><li><a class="toggle-vis" data-column="3">Status</a></li><li><a class="toggle-vis" data-column="4">Mark</a></li></ul></div>');
    $('a.toggle-vis').on( 'click', function (e) {
        e.preventDefault();
        var column = tableone.column( $(this).attr('data-column') );
        column.visible( ! column.visible() );
        console.log($(this).attr('data-column'));
    });
    //END dashboard datatables ellipsis menu tableone

    //Dashboard datatables ellipsis menu tabletwo
    $("div.toolbartwo").html('<div class="dropdown"><button class="btn btn-xs dropdown-toggle" type="button" id="dropdownMenucolvis" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-option-vertical"></span></button><ul class="dropdown-menu" aria-labelledby="dropdownMenucolvis"><li class="dropdown-header">Toggle columns</li><li><a class="toggle-vistwo" data-column="0">Serial#</a></li><li><a class="toggle-vistwo" data-column="1">Registration#</a></li><li><a class="toggle-vistwo" data-column="2">Mark</a></li><li><a class="toggle-vistwo" data-column="3">Owner</a></li><li><a class="toggle-vistwo" data-column="4">Due Date</a></li><li><a class="toggle-vistwo" data-column="5">Status</a></li><li><a class="toggle-vistwo" data-column="6">Action</a></li></ul></div>');
    $('a.toggle-vistwo').on( 'click', function (e) {
        e.preventDefault();
        var column = tabletwo.column( $(this).attr('data-column') );
        column.visible( ! column.visible() );
        console.log($(this).attr('data-column'));
    });
    //END dashboard datatables ellipsis menu tabletwo

    //.dashsection height = #announcedashsection height
    var h = $( 'div#announcedashsection' );
    var y = $( 'div#announce' );
    $('.dashsection:eq(0), .dashsection:eq(1)').css( 'min-height', (h.innerHeight() + y.innerHeight()) );
    $( window ).resize(function() {
        $('.dashsection:eq(0), .dashsection:eq(1)').css( 'min-height', (h.innerHeight() + y.innerHeight()) );
    });
    //END

    //start close (x) dashboard panels
    $('#dashsectionscontainer .closegspanels').click(function() {
        $( this ).parent().parent().parent().parent().parent().fadeOut( 'fast','swing');
    });
    //end close (x) dashboard panels

    //start close (x) gs + editowner + reviewattorney panels
    $('#gsselected .closegspanels, #editowner .closegspanels, #reviewattorney .closegspanels').click(function() {
        $( this ).parent().parent().parent().fadeOut( 'fast','swing');
    });
    //end close (x) gs + editowner + reviewattorney panels

    //START set initial checkboxes
    $('input[type=checkbox]').attr('checked',false);
    $('input[type=radio]').attr('checked',false);
    $('input[type=checkbox]#authemail, input[type=radio]#inlineRadio044').not(this).prop('checked', true);
    $('input[type=radio]#inlineRadio031').prop('checked', true);
    //if ($('input[type=checkbox]#authemail').prop('checked')) {
//	  console.log('checked');
//  }
//  else {
//	  console.log('unchecked');
//	  }
    //END set initial checkboxes

    //START additional info page supplemental register checked
    $('input[type=radio]#inlineRadio032').change(function() {
        var firstfees = $('li#firstli span').text();
        var classfee = $('li#classli span').text();
        var highestfeeperclass = '80';//additional fee
        var lastfees = (parseFloat(firstfees) + parseFloat( highestfeeperclass ));
        var lastfeesprincipal = (parseFloat(firstfees) + parseFloat( originalclassfees ));
        var originalclassfees = (parseFloat(classfee) - parseFloat( highestfeeperclass ));
        if(this.checked == true){
            $('li#classli span').html('<span>' + highestfeeperclass + '</span>');
            $('li#lastli span').html('<span>' + lastfees + '</span>');
            $('span#displayfees').html('<span>' + lastfees + '</span>');
            $('#tr div.alert').addClass('visuallyadded');
            $('#tr div.alert').removeClass('visuallyremoved');
        }
    });
    $('input[type=radio]#inlineRadio031').change(function() {
        var firstfees = $('li#firstli span').text();
        var classfee = $('li#classli span').text();
        var highestfeeperclass = '0';//additional fee
        var lastfees = (parseFloat(firstfees) + parseFloat( highestfeeperclass ));
        if(this.checked == true){
            $('li#classli span').html('<span>' + highestfeeperclass + '</span>');
            $('li#lastli span').html('<span>' + lastfees + '</span>');
            $('span#displayfees').html('<span>' + lastfees + '</span>');
            $('#tr div.alert').addClass('visuallyremoved');
            $('#tr div.alert').removeClass('visuallyadded');
        }
    });
    //END additional info page supplemental register checked

    //START enable / disable checkboxes
    //When 'Certification' is checked, 'Trademark / Servicemark', 'Collective' and 'Collective Membership' are disabled
    $('input#certcheck').change(function() {
        if(this.checked == true){
            $("input#tradeserv").prop({
                disabled: true,
                checked: false,
            });
            $("input#collectcheck").prop({
                disabled: true,
                checked: false,
            });
            $("input#collectmember").prop({
                disabled: true,
                checked: false,
            });
            $('.form-check#tradeserv2 span.radio').addClass('special');
            $('.form-check#collectcheck2 span.radio').addClass('special');
            $('.form-check#collectm2 span.radio').addClass('special');
            $('input#tradeserv').attr('aria-disabled', 'true');
            $('input#collectcheck').attr('aria-disabled', 'true');
            $('input#collectmember').attr('aria-disabled', 'true');
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
            $('.form-check#collectcheck2 span.radio').removeClass('special');
            $('.form-check#collectm2 span.radio').removeClass('special');
            $('input#tradeserv').attr('aria-disabled', 'false');
            $('input#collectcheck').attr('aria-disabled', 'false');
            $('input#collectmember').attr('aria-disabled', 'false');
        }
    });
    //When 'Collective' is checked, 'Certification' is disabled
    $('input#collectcheck').change(function() {
        if(this.checked == true){
            $('input#certcheck').prop({
                disabled: true,
                checked: false,
            });
            $('.form-check#certcheck2 span.radio').addClass('special');
            $('input#certcheck').attr('aria-disabled', 'true');
        }
        else if ($('input#collectmember').prop('checked')){
            $('input#certcheck').prop({
                disabled: true,
                checked: false,
            });
            $('.form-check#certcheck2 span.radio').addClass('special');
            $('input#certcheck').attr('aria-disabled', 'true');
        }
        else if ($('input#tradeserv').prop('checked')){
            $('input#certcheck').prop({
                disabled: true,
                checked: false,
            });
            $('.form-check#certcheck2 span.radio').addClass('special');
            $('input#certcheck').attr('aria-disabled', 'true');
        }
        else{
            $('input#certcheck').prop({
                disabled: false,
            });
            $('.form-check#certcheck2 span.radio').removeClass('special');
            $('input#certcheck').attr('aria-disabled', 'false');
        }
    });
    //When 'Collective Membership' is checked, 'Certification' is disabled
    $('input#collectmember').change(function() {
        if(this.checked == true){
            $('input#certcheck').prop({
                disabled: true,
                checked: false,
            });
            $('.form-check#certcheck2 span.radio').addClass('special');
            $('input#certcheck').attr('aria-disabled', 'true');
        }
        else if ($('input#collectcheck').prop('checked')){
            $('input#certcheck').prop({
                disabled: true,
                checked: false,
            });
            $('.form-check#certcheck2 span.radio').addClass('special');
            $('input#certcheck').attr('aria-disabled', 'true');
        }
        else if ($('input#tradeserv').prop('checked')){
            $('input#certcheck').prop({
                disabled: true,
                checked: false,
            });
            $('.form-check#certcheck2 span.radio').addClass('special');
            $('input#certcheck').attr('aria-disabled', 'true');
        }
        else{
            $('input#certcheck').prop({
                disabled: false,
            });
            $('.form-check#certcheck2 span.radio').removeClass('special');
            $('input#certcheck').attr('aria-disabled', 'false');
        }
    });
    //When 'Trademark / Servicemark' is checked, 'Certification' is disabled
    $('input#tradeserv').change(function(e) {
        if(this.checked == true){
            $('input#certcheck').prop({
                disabled: true,
                checked: false,
            });
            $('.form-check#certcheck2 span.radio').addClass('special');
            $('input#certcheck').attr('aria-disabled', 'true');
        }
        else{
            $('input#certcheck').prop({
                disabled: false,
            });
            $('.form-check#certcheck2 span.radio').removeClass('special');
            $('input#certcheck').attr('aria-disabled', 'false');
        }
    });//END enable / disable checkboxes

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

    //START input label height match
    var t = $( '.matchlabelheight' ).parent().prev('div').children('label');
    var u = $( '.matchlabelheighttwo' ).parent().prev('div').children('label');
    $('.matchlabelheight').css( 'height', (t.outerHeight() + 'px') );
    $('.matchlabelheighttwo').css( 'height', (u.outerHeight() + 'px') );
    //console.log(u.outerHeight());
    $( window ).resize(function() {
        $('.matchlabelheight').css( 'height', (t.outerHeight())  + 'px' );
        $('.matchlabelheighttwo').css( 'height', (u.outerHeight())  + 'px' );
    });
    //END input label height match

    //START basis input label height match upload label
    //var r = $( '#specdescript' );
//		var s = $( '#upimg' );
//		var z = $( '#specdescriptwo' );
//		var n = $( '#upimgtwo' );
//		var c = $( '#upimgfour' );
//		$( window ).load(function() {
//			$('#specdescript').css( 'height', ((s.outerHeight() + 2) + 'px') );
//			$('#specdescriptwo').css( 'height', ((n.outerHeight() + 2) + 'px') );
//			$('#specdescriptfour').css( 'height', ((c.outerHeight() + 2) + 'px') );
//		});
//		//console.log(s.outerHeight());
//		$( window ).resize(function() {
//			$('#specdescript').css( 'height', ((s.outerHeight() + 2)  + 'px') );
//			$('#specdescriptwo').css( 'height', ((n.outerHeight() + 2) + 'px') );
//			$('#specdescriptfour').css( 'height', ((c.outerHeight() + 2) + 'px') );
//		});
    //END basis input label height match upload label

    //Placeholder as editable text
    $('textarea#ta2, textarea#ta5').val('This mark consists of');
    //Placeholder as editable text

    //Placeholder as editable text edit owner
    $('input#ta12').val('Jackie').css('position','relative');
    $('input#ta13').val('Babos').css('position','relative');
    $('input#ta18').val('jackie.babos@us.gt.com').css('position','relative');
    $('input#ta14').val('333 John Carlyle St ').css('position','relative');
    $('input#ta15').val('# 500').css('position','relative');
    $('input#ta16').val('').css('position','relative');
    $('input#ta19').val('Alexandria').css('position','relative');
    $('input#ta020').val('Virginia').css('position','relative');
    $('input#ta021').val('22314').css('position','relative');
    $('input#ta17').val('703-562-6675').css('position','relative');
    $('input#ta022').val('Doing Business As (DBA)').css('position','relative');
    $('input#ta023').val('Jackie Z. Babos-Smith').css('position','relative');
    $('input#ta024').val('United States').css('position','relative');
    $('input#ta025').val('United States').css('position','relative');
    $('input#ta026').val('www.us.gt.com').css('position','relative');
    $('input#ta027').val('Cell').css('position','relative');
    $('input#ta028').val('').css('position','relative');

    $('input#ta212').val('Jacob').css('position','relative');
    $('input#ta213').val('Goldstein').css('position','relative');
    $('input#ta218').val('jacob.goldstein@us.gt.com').css('position','relative');
    $('input#ta214').val('333 John Carlyle St ').css('position','relative');
    $('input#ta215').val('# 500').css('position','relative');
    $('input#ta216').val('').css('position','relative');
    $('input#ta219').val('Alexandria').css('position','relative');
    $('input#ta2020').val('Virginia').css('position','relative');
    $('input#ta2021').val('22314').css('position','relative');
    $('input#ta217').val('703-562-6675').css('position','relative');
    $('input#ta2022').val('Also Known As (AKA)').css('position','relative');
    $('input#ta2023').val('Jacob T. Goldstein').css('position','relative');
    $('input#ta2024').val('Canada').css('position','relative');
    $('input#ta2025').val('Denmark').css('position','relative');
    $('input#ta2026').val('www.us.gt.com').css('position','relative');
    $('input#ta2027').val('Home').css('position','relative');
    $('input#ta2028').val('12345').css('position','relative');
    $('input#ta12, input#ta13, input#ta14, input#ta15, input#ta16, input#ta17, input#ta18, input#ta19, input#ta020, input#ta021, input#ta212, input#ta213, input#ta214, input#ta215, input#ta216, input#ta217, input#ta218, input#ta219, input#ta2020, input#ta2021, input#ta022, input#ta023, input#ta2022, input#ta2023, input#ta024, input#ta2024, input#ta025, input#ta2025, input#ta026, input#ta2026, input#ta027, input#ta028, input#ta2027, input#ta2028').prop('disabled', true);
    //Placeholder as editable text edit owner

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
        container: "body",
        placement : 'bottom auto',
    });
    //Initialize popover
    $('[data-toggle="popover"]').popover({
        title : false,
        html : true,
        placement : 'bottom auto',
        content : '<div class="media"><img src="images/stylized_alt2.gif" style="min-width:77px;width:80px;margin:0 auto;" class="media-object img-responsive" alt="my trademark"></div>'
    });
    $( '#togglepop' ).click(function() {
        $( this ).toggleClass( 'focus' );
        $(this).html() === '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Hide My Mark'
            ? $(this).html('<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Show My Mark')
            : $(this).html('<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Hide My Mark');
    });
    //Display uploaded filename
    $('input[type="file"]').change(function(e){
        var fileName = e.target.files[0].name;
        $('a.list-group-item').html('<span class="filename">' + fileName + '</span>' + '<span class="badge alert-success pull-right">File Uploaded</span><br clear:all>');
    });
    //toggle acceptance
    $( '#acceptreview' ).click(function() {
        $( this ).toggleClass( 'focus' );
        $(this).html() === 'Accept'
            ? $(this).html('Accepted')
            : $(this).html('Accept');
    });
    //sm file upload inputs
    $(document).on('change', 'input[type="file"]', function(e) {
        var fileDisplayArea = $(this).siblings('label').children('.fileDisplayArea');
        //var fileDisplayArea = ('.fileDisplayArea');
        var file = e.target.files[0];
        var imageType = /image.*/;
        if (file.type.match(imageType)) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $(fileDisplayArea).addClass('loader').html("");
                // Create an image
                var img = new Image();
                // Set the img src
                img.src = reader.result;
                // display the image on the page
                $(fileDisplayArea).removeClass('loader').append(img);
                $('.upload-drop-zone').css('height','115px');
                $('.upload-drop-zone img').css('height','64px');
            }
            reader.readAsDataURL(file);
        } else {
            var reader = new FileReader();
            reader.onload = function(e) {
                $(fileDisplayArea).addClass('loader').html("");
                // Create an image
                var arrayBuffer = reader.result;
                $(fileDisplayArea).removeClass('loader');
                $(fileDisplayArea).html('<span class="glyphicon glyphicon-check"></span>');
            }
            reader.readAsArrayBuffer(file);
        }
    });
    //lg file upload inputs
    $(document).on('change', 'input[type="file"]', function(e) {
        var fileInput = ('input#file');
        var fileDisplayArea = ('div#fileDisplayArea');
        var file = e.target.files[0];
        var imageType = /image.*/;
        if (file.type.match(imageType)) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $(fileDisplayArea).addClass('loader').html("");
                // Create an image
                var img = new Image();
                // Set the img src
                img.src = reader.result;
                // display the image on the page
                $(fileDisplayArea).removeClass('loader').append(img);
                $('.upload-drop-zone').css('height','115px');
                $('.upload-drop-zone img').css('height','64px');
            }
            reader.readAsDataURL(file);
        } else {
            var reader = new FileReader();
            reader.onload = function(e) {
                $(fileDisplayArea).addClass('loader').html("");
                // Create an image
                var arrayBuffer = reader.result;
                $(fileDisplayArea).removeClass('loader');
                $(fileDisplayArea).html('<span class="glyphicon glyphicon-check"></span>');
            }
            reader.readAsArrayBuffer(file);
        }
    });

    //+ Translation Item
    //

    //- Translation Item
    //

    //START toggle radio buttons content
    //start mark color options
    $('div#nocolorclaim').css('display','none');
    $('div#yescolorclaim').css('display','none');
    $('input#inlineRadio1').change(function() {
        if(this.checked == true){
            $('div#nocolorclaim').hide( 'fast' );
            $('div#yescolorclaim').show( 'slow' );
        }
        else {
            $('div#nocolorclaim').show( 'fast' );
            $('div#yescolorclaim').hide( 'slow' );
        }
    });
    $('input#inlineRadio2').change(function() {
        if(this.checked == true){
            $('div#nocolorclaim').show( 'fast' );
            $('div#yescolorclaim').hide( 'slow' );
        }
        else {
            $('div#nocolorclaim').hide( 'fast' );
            $('div#yescolorclaim').show( 'slow' );
        }
    });
    //end mark color options

    //start translations options
    $('div#yestranslation').css('display','none');
    $('input#inlineRadio3').change(function() {
        if(this.checked == true){
            $('div#yestranslation').show( 'slow' );
        }
        else {
            $('div#yestranslation').hide( 'slow' );
        }
    });
    $('input#inlineRadio4').change(function() {
        if(this.checked == true){
            $('div#yestranslation').hide( 'slow' );
        }
        else {
            $('div#yestranslation').show( 'slow' );
        }
    });
    //end translations options

    //start transliterations options
    $('div#yestransliteration').css('display','none');
    $('input#inlineRadio5').change(function() {
        if(this.checked == true){
            $('div#yestransliteration').show( 'fast' );
        }
        else {
            $('div#yestransliteration').hide( 'fast' );
        }
    });
    $('input#inlineRadio6').change(function() {
        if(this.checked == true){
            $('div#yestransliteration').hide( 'fast' );
        }
        else {
            $('div#yestransliteration').show( 'fast' );
        }
    });
    //end translations options

    //start nps options
    $('div#yesnps').css('display','none');
    $('input#inlineRadio7').change(function() {
        if(this.checked == true){
            $('div#yesnps').show( 'fast' );
        }
        else {
            $('div#yesnps').hide( 'fast' );
        }
    });
    $('input#inlineRadio8').change(function() {
        if(this.checked == true){
            $('div#yesnps').hide( 'fast' );
        }
        else {
            $('div#yesnps').show( 'fast' );
        }
    });
    //yes, contains name is checked
    $('div#yescontainsname').hide();
    $('#namechecked').change(function() {
        if(this.checked == true){
            $('div#yescontainsname').show( 'fast' );
        }
        else {
            $('div#yescontainsname').hide( 'fast' );
        }
    });
    //yes, contains portrait is checked
    $('div#yescontainsportrait').hide();
    $('#portraitchecked').change(function() {
        if(this.checked == true){
            $('div#yescontainsportrait').show( 'fast' );
        }
        else {
            $('div#yescontainsportrait').hide( 'fast' );
        }
    });
    //yes, contains signature is checked
    $('div#yescontainssignature').hide();
    $('#signaturechecked').change(function() {
        if(this.checked == true){
            $('div#yescontainssignature').show( 'fast' );
        }
        else {
            $('div#yescontainssignature').hide( 'fast' );
        }
    });
    //end nps options

    //start disclaimer options
    $('div#yesdisclaimer').css('display','none');
    $('input#inlineRadio09').change(function() {
        if(this.checked == true){
            $('div#yesdisclaimer').show( 'fast' );
        }
        else {
            $('div#yesdisclaimer').hide( 'fast' );
        }
    });
    $('input#inlineRadio010').change(function() {
        if(this.checked == true){
            $('div#yesdisclaimer').hide( 'fast' );
        }
        else {
            $('div#yesdisclaimer').show( 'fast' );
        }
    });
    //end disclaimer options

    //start uncommoninfo options
    $('div#yesnotpreviously').css('display','none');
    $('input#inlineRadio045').change(function() {
        if(this.checked == true){
            $('div#yesnotpreviously').show( 'fast' );
        }
        else {
            $('div#yesnotpreviously').hide( 'fast' );
        }
    });
    $('input#inlineRadio046').change(function() {
        if(this.checked == true){
            $('div#yesnotpreviously').hide( 'fast' );
        }
        else {
            $('div#yesnotpreviously').show( 'fast' );
        }
    });
    //end uncommoninfo options

    //start prior options
    $('div#yesprior').css('display','none');
    $('input#inlineRadio011').change(function() {
        if(this.checked == true){
            $('div#yesprior').show( 'fast' );
        }
        else {
            $('div#yesprior').hide( 'fast' );
        }
    });
    $('input#inlineRadio012').change(function() {
        if(this.checked == true){
            $('div#yesprior').hide( 'fast' );
        }
        else {
            $('div#yesprior').show( 'fast' );
        }
    });
    //end prior options

    //start prior options
    $('div#yesmeaning').css('display','none');
    $('input#inlineRadio013').change(function() {
        if(this.checked == true){
            $('div#yesmeaning').show( 'fast' );
        }
        else {
            $('div#yesmeaning').hide( 'fast' );
        }
    });
    $('input#inlineRadio014').change(function() {
        if(this.checked == true){
            $('div#yesmeaning').hide( 'fast' );
        }
        else {
            $('div#yesmeaning').show( 'fast' );
        }
    });
    //end prior options

    //start attorney options
    $('div#yesattorneyfiling').css('display','none');
    $('input#inlineRadio015').change(function() {
        if(this.checked == true){
            $('div#yesattorneyfiling').show( 'fast' );
            $( document ).find('p.required').toggleClass('visuallyshown');
        }
        else {
            $('div#yesattorneyfiling').hide( 'fast' );
        }
    });
    $('input#inlineRadio016').change(function() {
        if(this.checked == true){
            $('div#yesattorneyfiling').hide( 'fast' );
            $( document ).find('p.required').removeClass('visuallyshown');
        }
        else {
            $('div#yesattorneyfiling').show( 'fast' );
        }
    });
    //end attorney options

    //start us foreign options
    //us entity show
    $('div#yesusentity').css('display','none');
    $('input#inlineRadio019').change(function() {
        if(this.checked == true){
            $('div#yesusentity').show( 'fast' );

            $("input#inlineRadio020").prop({
                checked: false,
            });
        }
        else {
            $('div#yesusentity').hide( 'fast' );

            $("input#inlineRadio020").prop({
                checked: true,
            });
        }
    });
    $('input#inlineRadio020').change(function() {
        if(this.checked == true){
            $('div#yesusentity').hide( 'fast' );

            $("input#inlineRadio019").prop({
                checked: false,
            });
        }
        else {
            $('div#yesusentity').show( 'fast' );

            $("input#inlineRadio019").prop({
                checked: true,
            });
        }
    });

    //foreign entity show
    $('div#yesforeignentity').css('display','none');
    $('input#inlineRadio020').change(function() {
        if(this.checked == true){
            $('div#yesforeignentity').show( 'fast' );

            $("input#inlineRadio019").prop({
                checked: false,
            });
        }
        else {
            $('div#yesforeignentity').hide( 'fast' );

            $("input#inlineRadio019").prop({
                checked: true,
            });
        }
    });
    $('input#inlineRadio019').change(function() {
        if(this.checked == true){
            $('div#yesforeignentity').hide( 'fast' );

            $("input#inlineRadio020").prop({
                checked: false,
            });
        }
        else {
            $('div#yesforeignentity').show( 'fast' );

            $("input#inlineRadio020").prop({
                checked: true,
            });
        }
    });
    //end us foreign options

    //start basis commerce options
    $('div#yescommerce').css('display','none');
    $('input#inlineRadio25').change(function() {
        if(this.checked == true){
            $('div#yescommerce').show( 'fast' );
        }
        else {
            $('div#yescommerce').hide( 'fast' );
        }
    });
    $('input#inlineRadio26').change(function() {
        if(this.checked == true){
            $('div#yescommerce').hide( 'fast' );
        }
        else {
            $('div#yescommerce').show( 'fast' );
        }
    });
    //end basis commerce options

    //start basistwo connection options
    $('div#yesconnection').css('display','none');
    $('input#inlineRadio29').change(function() {
        if(this.checked == true){
            $('div#yesconnection').show( 'fast' );
            //$(heightmatch);
        }
        else {
            $('div#yesconnection').hide( 'fast' );
        }
    });
    $('input#inlineRadio30').change(function() {
        if(this.checked == true){
            $('div#yesconnection').hide( 'fast' );
        }
        else {
            $('div#yesconnection').show( 'fast' );
        }
    });
    //end basistwo connection options

    //start provide specimen options
    $('div#yesspecimenone').css('display','none');
    $('input#specimen').change(function() {
        if(this.checked == true){
            $('div#yesspecimenone').show( 'fast' );
        }
        else {
            $('div#yesspecimenone').hide( 'fast' );
        }
    });
    $('div#yesspecimentwo').css('display','none');
    $('input#specimentwo').change(function() {
        if(this.checked == true){
            $('div#yesspecimentwo').show( 'fast' );
        }
        else {
            $('div#yesspecimentwo').hide( 'fast' );
        }
    });
    //end provide specimen options
    //END toggle radio buttons content

    //start affiliation options
    $('div#usaffiliation').css('display','none');
    $('div#canadianaffiliation').css('display','none');
    $('#attorney-bar-standing').change(function(){
        $('.hidethis').hide( 'fast' );
        $('#' + $(this).val()).show( 'fast' );
    });
    //end affiliation options
    //start nameoftypeofbusiness options
    $( 'div#nametype' ).css('display','none');
    $( '#type' ).on('change',function(){
        $( 'div#nametype' ).show( 'fast' );
    });
    //end nameoftypeofbusiness options

    //START entity options (import concept)
    //us form
    $('#owner #autofill').css('display','none');
    $('#entype').on('change',function(){
        $('#container').empty();//maybe use detach
        $('footer').css('display','none');
        var include = ('js/' + $(this).val() + '.js');
        $.getScript( include );
        $('#autofill').show( 'fast' );
        $('footer').css('display','block');
    });
    //foreign form
    $('#owner #autofillforeign').css('display','none');
    $('#entypeforeignbahamas').on('change',function(){
        $('#containerforeign').empty();//maybe use detach
        $('footer').css('display','none');
        var includeforeign = ('js/' + $(this).val() + $( '#entitycountry' ).val() + '.js');
        $.getScript( includeforeign );
        $('#owner #autofillforeign').show( 'fast' );
        $('footer').css('display','block');
        //console.log(includeforeign);
    });
    //foreign select by country
    $('.albaniaform, .algeriaform, .angolaform, .bahamasform').css('display','none');
    $('#entitycountry').on('change',function(){
        var loadselectmenu = ('.' + $(this).val() + 'form');
        $('.hidethis').hide('fast');
        $( loadselectmenu ).show('slow').addClass('hidethis');
        $( loadselectmenu ).css('display','block');
    });
    //END entity options (import concept)

    //select signature method
    $('#direct').css('display','none');
    $('#signmethod').on('change',function(){
        var loadsign = ('#' + $(this).val());
        $('.hidethis').hide('fast');
        $( loadsign ).show('fast').addClass('hidethis');
        $( loadsign ).css('display','block');
        console.log(loadsign);
    });
    //END signature method

    //START fill from contacts values -- attorney
    function clearform() {
        $( 'input#attorney-first-name' ).val( '' );
        $( 'input#title' ).val( '' );
        $( 'input#attorney-last-name' ).val( '' );
        $( '#suffix' ).val( '');
        $( 'input#attorney-lawfirm-name' ).val( '' );
        $( '#attorney-country' ).val( '' );
        $( '#attorney-country' ).val( );
        $( 'input#attorney-address1' ).val( '' );
        $( 'input#attorney-city' ).val( '' );
        $( '#attorney-state' ).val('' );
        $( 'input#attorney-zipcode' ).val( '' );
        $( 'input#attorney-email' ).val( '' );
        $( 'input#attorney-phonenumbertype' ).val( '');
        $( 'input#attorney-phone' ).val( '' );
        $( '#mydata2' ).find('span.glyphicon-ok-sign').removeClass( 'glyphicon-ok-sign' ).parent().parent().css('background-color','#9BB8D3').siblings().css('background-color','#9BB8D3');
    }
    $( "a#clearform" ).click(clearform);
    $( "a#avo" ).click(function() {
        $( 'input#firstname' ).val( 'Avo' );
        $( 'input#title' ).val( 'Director, Enterprise Technology Strategy and Innovation' );
        $( 'input#attorney-last-name' ).val( 'Reid' );
        $( '#suffix' ).val( 1 );
        $( 'input#attorney-lawfirm-name' ).val( 'Grant Thornton' );
        $( '#attorney-country' ).val( 1 );
        $( 'input#attorney-address1' ).val( '333 John Carlyle St # 500' );
        $( 'input#attorney-city' ).val( 'Alexandria' );
        $( '#attorney-state' ).val( 4 );
        $( 'input#attorney-zipcode' ).val( '22314' );
        $( 'input#attorney-email' ).val( 'avo.reid@us.gt.com' );
        $( 'input#attorney-phonenumbertype' ).val( 4 );
        $( 'input#attorney-phone' ).val( '703-637-4097' );
    });
    $( "a#jacob" ).click(function() {
        $( 'input#attorney-first-name' ).val( 'Jacob' );
        $( 'input#title' ).val( 'ETS-GPS Senior Associate PS Advisory Practice' );
        $( 'input#attorney-last-name' ).val( 'Goldstein' );
        $( '#suffix' ).val( 1 );
        $( 'input#attorney-lawfirm-name' ).val( 'Grant Thornton' );
        $( '#attorney-country' ).val( 1 );
        $( 'input#attorney-address1' ).val( '333 John Carlyle St # 500' );
        $( 'input#attorney-city' ).val( 'Alexandria' );
        $( '#attorney-state' ).val( 4 );
        $( 'input#attorney-zipcode' ).val( '22314' );
        $( 'input#attorney-email' ).val( 'jacob.goldstein@us.gt.com' );
        $( 'input#attorney-phonenumbertype' ).val( 4 );
        $( 'input#attorney-phone' ).val( '571-444-1983' );
    });
    $( "a#jackie" ).click(function() {
        $( 'input#attorney-first-name' ).val( 'Jackie' );
        $( 'input#title' ).val( 'ADTA-GPS Associate PS Advisory Practice' );
        $( 'input#attorney-last-name' ).val( 'Babos' );
        $( '#suffix' ).val( 3 );
        $( 'input#attorney-lawfirm-name' ).val( 'Grant Thornton' );
        $( '#attorney-country' ).val( 1 );
        $( 'input#attorney-address1' ).val( '333 John Carlyle St # 500' );
        $( 'input#attorney-city' ).val( 'Alexandria' );
        $( '#attorney-state' ).val( 4 );
        $( 'input#attorney-zipcode' ).val( '22314' );
        $( 'input#attorney-email' ).val( 'jackie.babos@us.gt.com' );
        $( '#attorney-phonenumbertype' ).val( 4 );
        $( 'input#attorney-phone' ).val( '703-562-6675' );
    });
    //END fill from contacts values -- attorney

    //START auto-detect pre-fill
    //var arrlanguages = [ 'Spanish', 'German', 'French' ];
//	var arrlanguage = jQuery.makeArray( arrlanguages );
//	var arrlangtranslits = [ 'German', 'Korean', 'French', 'Spanish' ];
//	var arrlangtranslit = jQuery.makeArray( arrlangtranslits );
//	  $( '#detected textarea#ta2' ).val( 'Scrubby Butts Soap Co. Squeaky Clean Naturally!' );
//	  $( '#detected textarea#ta3' ).val( 'Red' );
//	  $( '#detected #language' ).val( arrlanguage[0] );
//	  $( '#detected #engtranslation' ).val('Jabón' );
//	  $( '#detected input#inthemark' ).val( 'Soap' );
//	  $( '#detected #languagetranslit' ).val( arrlangtranslit[1] );
//	  $( '#detected input#nonlatranslation' ).val( '비누' );
//	  $( '#detected input#inenglish' ).val( 'Soap' );
    //END auto-detect pre-fill

    //START contacts, fees, my mark components
    //start toggle glyphicon contacts widget
    function togglecontacts() {
        $( '#mydata2' ).find('span.glyphicon-ok-sign').parent().parent().css('background-color','#9BB8D3').siblings().css('background-color','#9BB8D3');
        //$( this ).parent().parent().find('glyphicon-ok-sign').toggleClass( 'glyphicon-plus-sign' );
        $( this ).find('span.glyphicon-plus-sign').toggleClass( 'glyphicon-ok-sign' );
        $( this ).find('span.glyphicon-ok-sign').parent().parent().css('background-color','#D4EB8E').siblings().css('background-color','#D4EB8E');
        $( this ).find('span.glyphicon-ok-sign').parent().parent().parent().siblings().children().children().children('.glyphicon-ok-sign').removeClass( 'glyphicon-ok-sign' );
    }
    $('a.fromcontact').click(togglecontacts);
    //start toggle glyphicon contacts widget

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
    $('button#autofill, button#autofillforeign').on('click',function() {
        $( '#mydata2' ).css('visibility','visible');
        $( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
        $( 'button#contactsbtn span#toggleglyphone' ).addClass('visuallyremoved');
        $( 'button#contactsbtn span#toggleglyph' ).addClass('visuallyadded');
    });
    //end show managed contacts from nav

    //edit owner accordion button toggle
    $('#editowner button.Accordion-trigger, #reviewattorney button.Accordion-trigger').click(function() {
        //$( this ).children('span.glyphicon-chevron-right').toggleClass('visuallyremoved');
        //$( this ).children('span.glyphicon-chevron-down').toggleClass('visuallyadded');
        $( this ).children('span.glyphicon-triangle-right').toggleClass('visuallyremoved');
        $( this ).children('span.glyphicon-triangle-bottom').toggleClass('visuallyadded');
    });
    //edit owner accordion button toggle

    //START additional phone
    $( 'button#addphone' ).on('click',function(){
        $( '.phones:eq(0)' ).clone().appendTo( '.appendphone' );
    });
    $( '#resetphone' ).on('click',function() {
        $( '.appendphone .phones' ).remove( '.phones:eq(0)' );
    });
    //END additional phone

    //START additional phone rev
    $( document ).on('click','button#addphone2',function(){
        $( 'div.phones:eq(0)' ).clone().appendTo( '.appendphones' );
        $( 'div.phones' ).last().find('input').val('');
        $( '.appendphones .resetphone2' ).removeClass( 'visuallyremoved' );
        $( this ).removeClass( '.addphoneinitial' );
    });
    $( document ).on('click','.resetphonebtn',function(){
        $( this ).parent().parent().parent().remove();
    });
    //END additional phone rev

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

    //START additional containsname
    $( 'button#addprior' ).on('click',function(){
        $( '.holdsaprior:eq(0)' ).clone().appendTo( '.appendaprior' );
    });
    $( '#resetprior' ).on('click',function () {
        $( '.appendaprior .holdsaprior' ).remove( '.holdsaprior:eq(0)' );
    });
    //END additional containsname

    //START pending + foreign
    $('div.holdsapending').css('display','none');
    $('div#pluspending').css('display','none');
    $('input#pendingtwo').change(function() {
        if(this.checked == true){
            $('div.holdsapending').show( 'fast' );
            $('div#pluspending').show( 'fast' );
        }
        else {
            $('div.holdsapending').hide( 'fast' );
            $('div#pluspending').hide( 'fast' );
        }
    });
    $('div.holdsaforeign').css('display','none');
    $('div#plusforeign').css('display','none');
    $('input#foreigntwo').change(function() {
        if(this.checked == true){
            $('div.holdsaforeign').show( 'fast' );
            $('div#plusforeign').show('fast');
        }
        else {
            $('div.holdsaforeign').hide( 'fast' );
            $('div#plusforeign').hide('fast');
        }
    });
    //END pending + foreign

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

    //START additional translate
    $( 'button#addtranslate' ).on('click',function(){
        $( '.holdsatranslate:eq(0)' ).clone().appendTo( '.appendatranslate' );
    });
    $( '#resettranslate' ).on('click',function () {
        $( '.appendatranslate .holdsatranslate' ).remove( '.holdsatranslate:eq(0)' );
    });
    //END additional translate

    //START additional transliteration
    $( 'button#addtransliterate' ).on('click',function(){
        $( '.holdsatransliterate:eq(0)' ).clone().appendTo( '.appendatransliterate' );
    });
    $( '#resettransliterate' ).on('click',function () {
        $( '.appendatransliterate .holdsatransliterate' ).remove( '.holdsatransliterate:eq(0)' );
    });
    //END additional transliteration

    //START additional concurrent app/reg
    $( 'button#addconcregapp' ).on('click',function(){
        $( '.holdsaconcregapp:eq(0)' ).clone().appendTo( '.appendaconcregapp' );
    });
    $( '#resetconcregapp' ).on('click',function () {
        $( '.appendaconcregapp .holdsaconcregapp' ).remove( '.holdsaconcregapp:eq(0)' );
    });
    //END additional concurrent app/reg

    //START modals
    $('#tradeservmodal','#collectivemodal','#collectivemembmodal','#loginmodal','#emailmodal','#securitymodal','#passwordmodal').on('shown.bs.modal', function () {
        $('.btn-success').focus();
    })
    //END modals

    //START show standard character preview
    $( '#ta2' ).keyup(function(){
        var d = $( '#entertext' ).next('div');
        var currentText = $(this).val();
        $( 'p#showmarktxt' ).text(currentText);
        $('#entertext, #entertext textarea').css( 'height', (d.innerHeight()) );
    });
    //var str = $( '#ta2' ).val();
//	$( 'button#displaytxt' ).on('click',function(){
//		$( 'p#displaytext' ).html( str );
//	});
    //END show standard character preview

    //START displaymark height match
    $( window ).load(function () {
        var d = $( '#entertext' ).next('div');
        $('#displaytext').css('display','flex').css('flex-direction','column');
        $('#entertext').css( 'min-height', (d.innerHeight()) );
        $( window ).resize(function() {
            $('#entertext').css( 'min-height', (d.innerHeight()) );
        });
    });
    //END displaymark height match

    //row header p height match
    var p = $('.rowheader p').height();
    $('.rowheader p').height(p);
    $( window ).resize(function() {
        $('.rowheader p').height(p);
    });
    //END row header p height match

    //row header h3 height match
    var q = $('.rowheader h3').height();
    $('.rowheader h3').height(q);
    $( window ).resize(function() {
        $('.rowheader h3').height(q);
    });
    //END row header p height match

    //tabpanels
    $('.tabwidget').tabs();
    $('ul[role="tablist"] a').on('click',function (){
        $(this).parent('li').toggleClass('notice');
        $(this).parent('li').siblings('li').removeClass('notice');
    });
    //tabpanels

    //toggle save more labels
    $('.togglesavemore').change(function() {
        if(this.checked == true){
            $( document ).find('span.subtle').removeClass('visuallyremoved');
            $( document ).find('#on').removeClass('visuallyhidden');
        }
        else if(this.checked == false) {
            $( document ).find('span.subtle').addClass( 'visuallyremoved' );
            $( document ).find('#on').addClass('visuallyhidden');
        }
    });
    //toggle save more labels

    //grid view checkboxes In-Use 1(a)
    //Gridview In-Use 1(a) Form Reveal
    $(document).on('change', 'input#otherformfirst, input#otherformcommerce', function() {
        $(this).parent().siblings('div#yesinuse1atwo').css('display','block' );
    });
    //END Gridview In-Use 1(a) Form Reveal

    //Toggle 1(a) + 1(b)
    $('#gridview table tbody tr td:nth-child(2) input').change(function() {
        if ($(this).prop('checked')) {
            $(this).parent().next('td').children('label').children('span').addClass('special');
            $(this).parent().next('td').children('input').prop({
                disabled: true,
                checked: false,
            });
            $(this).parent().siblings('td').children('input').attr('aria-disabled', 'false');
        }
        else if ($(this).prop('checked',false)) {
            $(this).parent().next('td').children('label').children('span').removeClass('special');
            $(this).parent().next('td').children('input').prop({
                disabled: false,
            });
            $(this).parent().siblings('td').children('input').attr('aria-disabled', 'false');
        }
    });
    $('#gridview table tbody tr th:nth-child(2) input').change(function() {
        if ($(this).prop('checked')) {
            $(this).parent().next('th').children('label').children('span').addClass('special');
            $(this).parent().next('th').children('input').prop({
                disabled: true,
                checked: false,
            });
            $(this).parent().next('th').children('input').attr('aria-disabled', 'true');
        }
        else if ($(this).prop('checked',false)) {
            $(this).parent().next('th').children('label').children('span').removeClass('special');
            $(this).parent().next('th').children('input').prop({
                disabled: false,
            });
            $(this).parent().next('th').children('input').attr('aria-disabled', 'false');
        }
    });
    $('#gridview table tbody tr td:nth-child(3) input').change(function() {
        if ($(this).prop('checked')) {
            $(this).parent().prev('td').children('label').children('span').addClass('special');
            $(this).parent().prev('td').children('input').prop({
                disabled: true,
                checked: false,
            });
            $(this).parent().siblings('td').children('input').attr('aria-disabled', 'true');
        }
        else if ($(this).prop('checked',false)) {
            $(this).parent().prev('td').children('label').children('span').removeClass('special');
            $(this).parent().prev('td').children('input').prop({
                disabled: false,
            });
            $(this).parent().siblings('td').children('input').attr('aria-disabled', 'false');
        }
    });
    $('#gridview table tbody tr th:nth-child(3) input').change(function() {
        if ($(this).prop('checked')) {
            $(this).parent().prev('th').children('label').children('span').addClass('special');
            $(this).parent().prev('th').children('input').prop({
                disabled: true,
                checked: false,
            });
            $(this).parent().prev('th').children('input').attr('aria-disabled', 'true');
        }
        else if ($(this).prop('checked',false)) {
            $(this).parent().prev('th').children('label').children('span').removeClass('special');
            $(this).parent().prev('th').children('input').prop({
                disabled: false,
            });
            $(this).parent().prev('th').children('input').attr('aria-disabled', 'false');
        }
    });
    //END Toggle 1(a) + 1(b)

    //user selects In-Use 1(a) classname and adjacent checkbox
    $(document).on('change', '#gridview table tbody tr th:nth-child(2) input', function() { //Classnames 2nd checkbox
        var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
        var p 	= 	$( '#gridview table tbody tr th:nth-child(2) input:checked' ); //Classnames 2nd checkbox, checked
        var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(2) input' ); //In-Use 1(a) checkbox
        var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><!--toggle 1(a) two--><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
        console.log('#gridview table tbody tr th:nth-child(2) input');
        if ($(this).prop('checked')) {
            $(q).prop({
                checked: true,
            });
            $(this).parent().prev('th').children('input').prop({
                checked: true,
            });
            $(this).parent().parent().after(newrow);
            $('div#yesinuse1atwo').css('display','none');
        }
        if (this.checked == false) {
            $(q).prop({
                checked: false,
            });
            $(this).parent().parent().next('tr').remove();
        }
    });
    //user selects Intent-to-Use 1(b) classname and adjacent checkbox
    $(document).on('change', '#gridview table tbody tr th:nth-child(3) input', function() { //Classnames 3rd checkbox
        var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
        var p 	= 	$( '#gridview table tbody tr th:nth-child(3) input:checked' ); //Classnames 3rd checkbox, checked
        var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(3) input' ); //Intent-to-Use 1(b) checkbox
        if ($(this).prop('checked')) {
            $(q).prop({
                checked: true,
            });
            $(this).parent().prev('th').prev('th').children('input').prop({
                checked: true,
            });
        }
        if (this.checked == false) {
            $(q).prop({
                checked: false,
            });
        }
    });
    //user selects Foreign Application 44(d) classname and adjacent checkbox
    $(document).on('change', '#gridview table tbody tr th:nth-child(4) input', function() { //Classnames 3rd checkbox
        var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
        var p 	= 	$( '#gridview table tbody tr th:nth-child(3) input:checked' ); //Classnames 3rd checkbox, checked
        var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(4) input' ); //Foreign Application 44(d) checkbox
        var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
        if ($(this).prop('checked')) {
            $(q).prop({
                checked: true,
            });
            $(this).parent().prev('th').prev('th').prev('th').children('input').prop({
                checked: true,
            });
            $(this).parent().parent().after(newrow);
            $('div#yesinuse1atwo').css('display','none');
        }
        if (this.checked == false) {
            $(q).prop({
                checked: false,
            });
            $(this).parent().parent().next('tr').remove();
        }
    });
    //user selects Foreign Registration 44(e) classname and adjacent checkbox
    $(document).on('change', '#gridview table tbody tr th:nth-child(5) input', function() { //Classnames 3rd checkbox
        var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
        var p 	= 	$( '#gridview table tbody tr th:nth-child(3) input:checked' ); //Classnames 3rd checkbox, checked
        var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(5) input' ); //Foreign Registration 44(e) checkbox
        var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
        if ($(this).prop('checked')) {
            $(q).prop({
                checked: true,
            });
            $(this).parent().prev('th').prev('th').prev('th').prev('th').children('input').prop({
                checked: true,
            });
            $(this).parent().parent().after(newrow);
            $('div#yesinuse1atwo').css('display','none');
        }
        if (this.checked == false) {
            $(q).prop({
                checked: false,
            });
            $(this).parent().parent().next('tr').remove();
        }
    });
    //END user selects classname and adjacent checkbox
    $(document).on('change', '#gridview table thead tr:nth-child(2) th:nth-child(2) input', function() { //In-Use 1(a) checkbox
        var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
        var p 	= 	$( '#gridview table tbody tr th:nth-child(2) input' ); //Classnames 2nd checkbox
        var pp	=	$( '#gridview table tbody tr th:nth-child(3) input' );
        var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
        var y	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(3) input' ); //Intent-To-Use 1(b) checkbox
        var d 	= 	$( '#gridview table tbody tr td:nth-child(3) input' ); //GS 2nd checkbox
        var dd 	= 	$( '#gridview table tbody tr td:nth-child(2) input' ); //GS 1st checkbox
        var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><!--toggle 1(a) two--><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
        var newrowgs1a = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
        //user selects classnames then selects In-Use 1(a)
        if ($(b).prop('checked')) {
            $(b).parent().next('th').children('input').prop({
                checked: true,
            });
            $( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
            $('div#yesinuse1atwo').css('display','none');
            console.log(dd);
        }
        //user selects In-Use 1(a) and all Classnames + GSs are selected
        else if ($(b).prop('checked',false)) {
            $(p).prop({
                checked: true,
            });
            $(b).prop({
                checked: true,
            });
            $(c).prop({
                checked: true,
            });
            $(dd).prop({
                checked: true,
            });
            $(dd).parent().parent().after(newrow);
            $( document ).find('tr:contains(Class)').after(newrowgs1a);
            $('div#yesinuse1atwo').css('display','none');
            $('div#optionuploadtwo').css('display','block');
        }
        //disable
        if ($(this).prop('checked')) {
            $(y).prop({
                disabled: true,
                checked: false,
            });
            $(y).attr('aria-disabled', 'true');
            $( '#gridview table thead tr:nth-child(2) th:nth-child(3) label span' ).addClass('special');
            $(pp).prop({
                disabled: true,
                checked: false,
            });
            $(pp).attr('aria-disabled', 'true');
            $( '#gridview table tbody tr th:nth-child(3) label span' ).addClass('special');
            $(d).prop({
                disabled: true,
                checked: false,
            });
            $(d).attr('aria-disabled', 'true');
            $( '#gridview table tbody tr td:nth-child(3) label span' ).addClass('special');
        }

        if (this.checked == false) {
            $(b).parent().next('th').children('input').prop({
                checked: false,
            });
            $(p).prop({
                checked: false,
            });
            $(dd).prop({
                checked: false,
            });
            $(y).prop({
                disabled: false,
            });
            $( '#gridview table thead tr:nth-child(2) th:nth-child(3) label span' ).removeClass('special');
            $(pp).prop({
                disabled: false,
            });
            $(pp).attr('aria-disabled', 'false');
            $( '#gridview table tbody tr th:nth-child(3) label span' ).removeClass('special');
            $(d).prop({
                disabled: false,
            });
            $(d).attr('aria-disabled', 'false');
            $( '#gridview table tbody tr td:nth-child(3) label span' ).removeClass('special');
            $(c).prop({
                checked: false,
            });
            $( document ).find('.inuse1aone').remove('.inuse1aone');
            $('div#optionuploadtwo').css('display','none');
            $( document ).find('.optionalupload').remove('.optionalupload');
        }
    });

    //grid view checkboxes Intent-to-Use 1(b)
    $(document).on('change', '#gridview table thead tr:nth-child(2) th:nth-child(3) input', function() { //Intent-to-Use 1(b) checkbox
        var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
        var p 	= 	$( '#gridview table tbody tr th:nth-child(3) input' ); //Classnames 3rd checkbox
        var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
        var d 	= 	$( '#gridview table tbody tr td:nth-child(2) input' ); //GS 1st checkbox
        var dd 	= 	$( '#gridview table tbody tr td:nth-child(3) input' ); //GS 2nd checkbox
        var ee	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(2) input' )
        var ff	=	$( '#gridview table tbody tr th:nth-child(2) input' ); //Classnames 2nd checkbox
        //user selects classnames then selects Intent-to-Use 1(b)
        if ($(b).prop('checked')) {
            $(b).parent().next('th').next('th').children('input').prop({
                checked: true,
            });
            $(b).parent().next('th').children('input').prop({  //disable
                disabled: true,
                checked: false,
            });
            $(d).parent().parent().next('.optionalupload').hide('.optionalupload');
            //disable
            $(d).prop({
                disabled: true,
                checked: false,
            });
            $(d).attr('aria-disabled', 'true');
            $( '#gridview table tbody tr td:nth-child(2) label span' ).addClass('special');
            $(ff).prop({
                disabled: true,
                checked: false,
            });
            $(ff).attr('aria-disabled', 'true');
            $( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special');
            $(b).parent().next('th').children('input').attr('aria-disabled', 'true');
            $( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special');
            $( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special');
        }
        //user selects Intent-to-Use 1(b) and all Classnames + GSs are selected
        else if($(b).prop('checked',false)) {
            $(p).prop({
                checked: true,
            });
            $(b).prop({
                checked: true,
            });
            $(c).prop({
                checked: true,
            });
            $(dd).prop({
                checked: true,
            });
            //disable
            $(b).parent().next('th').children('input').prop({
                disabled: true,
                checked: false,
            });
            $(d).parent().parent().next('.optionalupload').hide('.optionalupload');
            $(d).prop({
                disabled: true,
                checked: false,
            });
            $(d).attr('aria-disabled', 'true');
            $( '#gridview table tbody tr td:nth-child(2) label span' ).addClass('special');

            $(ee).prop({
                disabled: true,
                checked: false,
            });
            $(ee).attr('aria-disabled', 'true');
            $( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special');

            $(ff).prop({
                disabled: true,
                checked: false,
            });
            $(ff).attr('aria-disabled', 'true');
            $( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special');

            $(b).parent().next('th').children('input').attr('aria-disabled', 'true');
            $( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special');
            //disable
            $( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special');
        }
        if (this.checked == false) {
            $(p).prop({
                checked: false,
            });
            $(b).prop({
                checked: false,
            });
            $(c).prop({
                checked: false,
            });
            $(dd).prop({
                checked: false,
            });
            //enable
            $(d).prop({
                disabled: false,
            });
            $(d).attr('aria-disabled', 'false');
            $( '#gridview table tbody tr td:nth-child(2) label span' ).removeClass('special');
            $(ee).prop({
                disabled: false,
            });
            $(ee).attr('aria-disabled', 'false');
            $( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).removeClass('special');
            $(ff).prop({
                disabled: false,
            });
            $(ff).attr('aria-disabled', 'false');
            $( '#gridview table tbody tr th:nth-child(2) label span' ).removeClass('special');

            $(b).parent().next('th').children('input').attr('aria-disabled', 'false');
            $( '#gridview table tbody tr th:nth-child(2) label span' ).removeClass('special');
            $( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).removeClass('special');
        }
    });

    //grid view checkboxes Foreign Application 44(d)
    $(document).on('change', '#gridview table thead tr:nth-child(2) th:nth-child(4) input', function() { //Foreign Application 44(d) checkbox
        var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
        var p 	= 	$( '#gridview table tbody tr th:nth-child(4) input' ); //Classnames 4th checkbox
        var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
        var dd 	= 	$( '#gridview table tbody tr td:nth-child(4) input' ); //GS 3rd checkbox
        var newrow = $( "<tr class='foreignapp'><td colspan='5'><div class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='country'>Foreign Application Country</label><select class='form-control' id='country'><option value='Select'>Select</option><option value='Mexico'>Mexico</option><option value='Canada'>Canada</option><option value='United Kingdom'>United Kingdom</option></select></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappno'>Foreign Application Number</label><input type='textarea' class='form-control' id='foreignappno' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappdate'>Foreign Application Filing Date</label><input type='date' class='form-control' id='foreignappdate' value=''></div></div></td></tr>" );
        var newrowgs44d = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
        //user selects classnames then selects Foreign Application 44(d)
        if ($(b).prop('checked')) {
            $(b).parent().next('th').next('th').next('th').children('input').prop({
                checked: true,
            });
            $( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
        }
        //user selects Foreign Application 44(d) and all Classnames  + GSs are selected
        else if ($(b).prop('checked',false)) {
            $(p).prop({
                checked: true,
            });
            $(b).prop({
                checked: true,
            });
            $(c).prop({
                checked: true,
            });
            $(dd).prop({
                checked: true,
            });
            $(dd).parent().parent().after(newrowgs44d);
            $( document ).find('tr:contains(Class)').after(newrow);
            $('div#yesinuse1atwo').css('display','none');
            $('div#optionuploadtwo').css('display','block');
        }
        if (this.checked == false) {
            $(p).prop({
                checked: false,
            });
            $(dd).prop({
                checked: false,
            });
            $(c).prop({
                checked: false,
            });
            $(b).parent().parent().siblings('.foreignapp').remove('.foreignapp');
            $( document ).find('.inuse1aone').remove('.inuse1aone');
            $('div#optionuploadtwo').css('display','none');
            $( document ).find('.optionalupload').remove('.optionalupload')
        }
    });

    //grid view checkboxes Foreign Registration 44(e)
    $(document).on('change', '#gridview table thead tr:nth-child(2) th:nth-child(5) input', function() { //Foreign Registration 44(e) checkbox
        var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
        var p 	= 	$( '#gridview table tbody tr th:nth-child(5) input' ); //Classnames 5th checkbox
        var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
        var dd 	= 	$( '#gridview table tbody tr td:nth-child(5) input' ); //GS 4th checkbox
        var newrow = $( "<tr class='foreignregi'><td colspan='5'><div class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='country'>Foreign Registration Country</label><select class='form-control' id='country'><option value='Select'>Select</option><option value='Mexico'>Mexico</option><option value='Canada'>Canada</option><option value='United Kingdom'>United Kingdom</option></select></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappno'>Foreign Registration Number</label><input type='textarea' class='form-control' id='foreignappno' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappdate'>Foreign Registration Exp. Date</label><input type='date' class='form-control' id='foreignappdate' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></td></tr>" );
        var newrowgs44e = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
        //user selects classnames then selects Foreign Registration 44(e)
        if ($(b).prop('checked')) {
            $(b).parent().next('th').next('th').next('th').next('th').children('input').prop({
                checked: true,
            });
            $( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
        }
        //user selects Foreign Registration 44(e) and all Classnames + GSs are selected
        else if ($(b).prop('checked',false)) {
            $(p).prop({
                checked: true,
            });
            $(b).prop({
                checked: true,
            });
            $(c).prop({
                checked: true,
            });
            $(dd).prop({
                checked: true,
            });
            $(dd).parent().parent().after(newrowgs44e);
            $( document ).find('tr:contains(Class)').after(newrow);
            $('div#yesinuse1atwo').css('display','none');
            $('div#optionuploadtwo').css('display','block');
        }
        if (this.checked == false) {
            $(p).prop({
                checked: false,
            });
            $(c).prop({
                checked: false,
            });
            $(dd).prop({
                checked: false,
            });
            $(b).parent().parent().siblings('.foreignregi').remove('.foreignregi');
            $( document ).find('.inuse1aone').remove('.inuse1aone');
            $('div#optionuploadtwo').css('display','none');
            $( document ).find('.optionalupload').remove('.optionalupload')
        }
    });

    //grid view GS checkboxes In-Use 1(a)
    $(document).on('change', 'input#optionupload', function() {
        if (this.checked == true) {
            $('div#optionuploadtwo').css('display','block');
        }
        else if (this.checked == false) {
            $('div#optionuploadtwo').css('display','none');
        }
    });

    $(document).on('change', '#gridview table tbody tr td:nth-child(2) input', function() { //In-Use 1(a) GS checkbox
        var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
        var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
        if (this.checked == true) {
            $(this).parent().parent().after(newrow);
            $('div#optionuploadtwo').css('display','none');
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
                checked: true,
            });
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').children('input').prop({
                checked: true,
            });
        }
        else if (this.checked == false) {
            $(this).parent().parent().siblings('.optionalupload').remove('.optionalupload');
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
                checked: false,
            });
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').children('input').prop({
                checked: false,
            });
            $('div#optionuploadtwo').css('display','none');
        }
    });

    //grid view GS checkboxes Intent-to-Use 1(b)
    $(document).on('change', '#gridview table tbody tr td:nth-child(3) input', function() { //Intent-to-Use 1(b) GS checkbox
        var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
        if (this.checked == true) {
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
                checked: true,
            });
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').children('input').prop({
                checked: true,
            });
        }
        else if (this.checked == false) {
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
                checked: false,
            });
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').children('input').prop({
                checked: false,
            });
        }
    });

    //grid view GS checkboxes Foreign Application 44(d)
    $(document).on('change', '#gridview table tbody tr td:nth-child(4) input', function() { //Foreign Application 44(d) GS checkbox
        var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
        var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
        if (this.checked == true) {
            $(this).parent().parent().after(newrow);
            $('div#optionuploadtwo').css('display','none');
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
                checked: true,
            });
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').next('th').children('input').prop({
                checked: true,
            });
        }
        else if (this.checked == false) {
            $(this).parent().parent().siblings('.optionalupload').remove('.optionalupload');
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
                checked: false,
            });
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').next('th').children('input').prop({
                checked: false,
            });
            $('div#optionuploadtwo').css('display','none');
        }
    });

    //grid view GS checkboxes Foreign Registration 44(e)
    $(document).on('change', '#gridview table tbody tr td:nth-child(5) input', function() { //Foreign Registration 44(e) GS checkbox
        var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
        var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
        if (this.checked == true) {
            $(this).parent().parent().after(newrow);
            $('div#optionuploadtwo').css('display','none');
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
                checked: true,
            });
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').next('th').next('th').children('input').prop({
                checked: true,
            });
        }
        else if (this.checked == false) {
            $(this).parent().parent().siblings('.optionalupload').remove('.optionalupload');
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
                checked: false,
            });
            $( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').next('th').next('th').children('input').prop({
                checked: false,
            });
            $('div#optionuploadtwo').css('display','none');
        }
    });


    //$( 'input#iu1a' ).change(function() {
//		if(this.checked == true){
//			console.log('checked');
//			}
//		if(this.checked == false){
//		console.log('unchecked');
//		}
//	});
    //END grid view checkboxes


    //END Gridview In-Use 1(a) Form Reveal
});