package com.battlenex.game.dialogues;

import java.util.ArrayList;
import java.util.Arrays;

import com.battlenex.game.Client;


public class DialogueContainer {
	private Client c;
	private int optionId;
	private DialogueAction action;
	private ArrayList<OptionDialogue> optionDialogues;
	private int currentDialogueId = 1;
	private boolean closeInterface = true;

	public static void CreateDialogue(Client c, DialogueAction action, String... lines) {
		if (c.nextChat > 0) {
			c.nextChat = -1;
		}
		c.dialogueContainer = new DialogueContainer(c, action, lines);
	}

	private DialogueContainer(Client c, DialogueAction action, String... lines) {
		this.c = c;
		this.action = action;
		this.optionDialogues = new ArrayList<OptionDialogue>();
		this.showOptions(true, lines);
		this.action.preExecution(this);
	}

	public void showOptions(int dialogueId) {
		for (int i = 0; i < optionDialogues.size(); i++) {
			if (i == (dialogueId - 1)) {
				optionDialogues.get(i).display(c);
				currentDialogueId = dialogueId;
				closeInterface = false;
				break;
			}
		}
	}

	public void addAdditionalOptions(String... lines) {
		showOptions(false, lines);
	}

	private void showOptions(boolean display, String... lines) {
		OptionDialogue optionDialogue = null;
		int id = 1;
		for (OptionDialogue i : optionDialogues) {
			if (Arrays.equals(i.getLines(), lines)) {
				optionDialogue = i;
				currentDialogueId = id;
				break;
			}
			id++;
		}
		if (optionDialogue == null) {
			optionDialogue = new OptionDialogue(lines);
			optionDialogues.add(optionDialogue);
			id = optionDialogues.size();
		}
		if (display) {
			currentDialogueId = id;
			optionDialogue.display(c);
		}
	}

	public void closeInterface(boolean value) {
		this.closeInterface = value;
	}

	public void execute(int optionId) {
		this.optionId = optionId;
		action.execute(this);

		if (closeInterface) {
			c.getPA().closeAllWindows();
		} else {
			closeInterface = true;
		}
	}

	public int getOptionId() {
		return optionId;
	}

	public int getCurrentDialogueId() {
		return currentDialogueId;
	}
}
