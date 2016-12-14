package org.cubeville.cvtools.commands.commandmap.commandmaptypes;

public interface CommandMapType {
	
	public default boolean isBlock(Object object) {
		return false;
	}
}
