package com.battlenex.game.players.packets;

/**
 * @author Ryan / Lmctruck30
 */

import com.battlenex.game.Client;
import com.battlenex.game.items.UseItem;
import com.battlenex.game.players.PacketType;

public class ItemOnObject implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		/*
		 * a = ? b = ?
		 */

		c.getInStream().readUnsignedWord();
		int objectId = c.getInStream().readSignedWordBigEndian();
		int objectY = c.getInStream().readSignedWordBigEndianA();
		c.getInStream().readUnsignedWord();
		int objectX = c.getInStream().readSignedWordBigEndianA();
		int itemId = c.getInStream().readUnsignedWord();
		if (!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		UseItem.ItemonObject(c, objectId, objectX, objectY, itemId);

	}

}
