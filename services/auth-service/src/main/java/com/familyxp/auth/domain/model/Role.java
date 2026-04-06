package com.familyxp.auth.domain.model;

/**
 * Defines the user roles within the FamilyXP ecosystem.
 */
public enum Role {
    /**
     * Parent role - Aggregate owner, can create tasks and manage family.
     */
    PARENT,
    
    /**
     * Child role - Dependent entity, can complete tasks and earn rewards.
     */
    CHILD
}
