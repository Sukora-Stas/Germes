package org.itsimulator.germes.app.model.entity.person;

import org.itsimulator.germes.app.model.entity.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity that encapsulates user of the application
 */

@Table(name = "ACCOUNT")
@Entity
public class Account extends AbstractEntity {

}
