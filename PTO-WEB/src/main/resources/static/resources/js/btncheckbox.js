/**
   * togglePressed() toggles the aria-pressed atribute between true or false
   *
   * @param ( id object) button to be operated on
   *
   * @return N/A
   */
  function togglePressed(id) {
  
    // reverse the aria-pressed state
    if ($(id).attr('aria-pressed') == 'false') {
      $(id).attr('aria-pressed', 'true');
    }
    else {
      $(id).attr('aria-pressed', 'false');
    }
  }