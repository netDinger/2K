package com.dingar.twok.phae.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**A custom scope for feature*/
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureScope {
}
