package com.dingar.twok.dice.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
/**A custom scope for Auth feature*/
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureScope {
}
