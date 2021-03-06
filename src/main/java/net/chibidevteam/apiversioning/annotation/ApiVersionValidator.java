package net.chibidevteam.apiversioning.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.chibidevteam.apiversioning.config.ApiVersioningConfiguration;
import net.chibidevteam.apiversioning.util.helper.VersionHelper;

public class ApiVersionValidator implements ConstraintValidator<ValidApiVersion, String> {

    private String[] allowedVersions;

    @Override
    public void initialize(ValidApiVersion annotation) {
        allowedVersions = ApiVersioningConfiguration.getSupportedVersions();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        if (!VersionHelper.isVersion(value)) {
            return false;
        }
        return VersionHelper.match(value, allowedVersions, true);
    }

}
