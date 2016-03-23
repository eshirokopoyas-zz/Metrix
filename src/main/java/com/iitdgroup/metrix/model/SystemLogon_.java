package com.iitdgroup.metrix.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-18T16:11:20.429+0300")
@StaticMetamodel(SystemLogon.class)
public class SystemLogon_ {
	public static volatile SingularAttribute<SystemLogon, Long> id;
	public static volatile SingularAttribute<SystemLogon, Date> timestamp;
	public static volatile SingularAttribute<SystemLogon, String> cookie;
	public static volatile SingularAttribute<SystemLogon, String> user_agent;
}
