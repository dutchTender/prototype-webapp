// Hide / Show Button
function() {
// toggle the region
this.$region.slideToggle(this.toggleSpeed, function() {
  if ($(this).attr('aria-expanded') == 'false') { // region is collapsed
	// update the aria-expanded attribute of the region
	$(this).attr('aria-expanded', 'true');
	// move focus to the region
	$(this).focus();
	// update the button label
  }
  else { // region is expanded
	// update the aria-expanded attribute of the region
	$(this).attr('aria-expanded', 'false');
	// update the button label
  }
});
} // end toggleRegion()