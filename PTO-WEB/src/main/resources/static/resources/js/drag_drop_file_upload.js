+ function($) {
    'use strict';

    // UPLOAD CLASS DEFINITION
    // ======================

    //var dropZone = document.getElementById('drop-zone');
    //var uploadForm = document.getElementById('js-upload-form');

    //var startUpload = function(files) {
        //console.log(files)
		//var fileName = files[0].name;
            //alert('The file "' + fileName +  '" has been selected.');
			//$('a.list-group-item').html('<span class="filename">' + files[0].name + '</span>' + '<span class="badge alert-success pull-right">Success</span>')
    //}
	//if user clicks, do this
	$('input[type="file"]').change(function(e){
            var fileName = e.target.files[0].name;
            //alert('The file "' + fileName +  '" has been selected.');
			$('a.list-group-item').html('<span class="filename">' + fileName + '</span>' + '<span class="badge alert-success pull-right">Success</span>')
        });
	
    //uploadForm.addEventListener('submit', function(e) {
        //var uploadFiles = document.getElementById('js-upload-files').files;
        //e.preventDefault()

        //startUpload(uploadFiles)
    //})

    //dropZone.ondrop = function(e) {
        //e.preventDefault();
        //this.className = 'upload-drop-zone col-xs-12 col-md-10 col-lg-8'; //this is where to add the classname appended with the filename as background-image

        //startUpload(e.dataTransfer.files)
    //}

    //dropZone.ondragover = function() {
       //this.className = 'upload-drop-zone drop col-xs-12 col-md-10 col-lg-8';
        //return false;
    //}

    //dropZone.ondragleave = function() {
       // this.className = 'upload-drop-zone';
       // return false;
    //}
	
	//display file_icon
	     //function readURL(input) {
//            if (input.files && input.files[0]) {
//                var reader = new FileReader();
//
//                reader.onload = function (e) {
//                    $('#fileimage')
//                        .attr('src', e.target.result);
//                };
//
//                reader.readAsDataURL(input.files[0]);
//            }
//        }

}(jQuery);