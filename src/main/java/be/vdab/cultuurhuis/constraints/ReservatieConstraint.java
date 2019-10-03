package be.vdab.cultuurhuis.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = ReservatieConstraintValidator.class)
public @interface ReservatieConstraint {

    String message() default "{be.vdab.cultuurhuis.constraints.ReservatieConstraint.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
