package com.thorton.grant.uspto.prototypewebapp.interfaces.registration;

import com.thorton.grant.uspto.prototypewebapp.interfaces.registration.PasswordMatches;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.UserCredentialsDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
   public void initialize(PasswordMatches constraint) {
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
      UserCredentialsDTO user = (UserCredentialsDTO) obj;
      return user.getPassword().equals(user.getMatchingPassword());

   }
}
