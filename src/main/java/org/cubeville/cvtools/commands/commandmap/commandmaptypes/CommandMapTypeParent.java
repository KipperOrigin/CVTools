package org.cubeville.cvtools.commands.commandmap.commandmaptypes;

import org.bukkit.block.Block;

public class CommandMapTypeParent implements CommandMapType {

	public boolean isBlock(Object object) {
		if (object instanceof Block)
			return true;
		else
			return false;
	}
}
