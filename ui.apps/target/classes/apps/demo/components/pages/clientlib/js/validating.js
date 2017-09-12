
Example = function() {
   return {
        
      // Dialog field cross validation.
      checkFields : function(dialog,maxWidth,maxHeight) {
         var textfieldArray = dialog.findByType("textfield");
         var textfieldLength0 = textfieldArray[0].getValue().length;
         var textfieldLength1 = textfieldArray[1].getValue().length;
            
         if((textfieldLength0 > 0 && textfieldLength1 > 0) ||
            (textfieldLength0 === 0 && textfieldLength1 === 0)) {
            // Both fields have or do not have a value
            return true;
         } else {
            // Cross validation fails
            CQ.Notification.notify(CQ.I18n.getMessage("Validation Error"),
                                   CQ.I18n.getMessage("Both fields must be filled or left empty."));
            return false;  
         }
      }
   };
}();