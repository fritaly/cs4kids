/* *******************************************************
 * © 1996-2009 HR Access Solutions. All rights reserved
 * ******************************************************/

package fr.ritaly.cs4kids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public enum Quizz {
	QUIZZ1("Les chiens n'aiment pas trop les chats", "chiens", "chiens",
			"motos", "légumes", "tomates", "maisons");

	private Quizz(String sentence, String word, String... proposals) {
		this.sentence = sentence;
		this.word = word;
		this.proposals.addAll(Arrays.asList(proposals));
	}

	private final String sentence;

	private final String word;

	private final List<String> proposals = new ArrayList<String>();

	public String getSentence() {
		return sentence;
	}

	public String getIncompleteSentence() {
		return StringUtils.replace(sentence, word, StringUtils.repeat("_", word
				.length()));
	}

	public String getWord() {
		return word;
	}

	public List<String> getProposals() {
		final List<String> list = new ArrayList<String>(proposals);

		Collections.shuffle(list);

		return list;
	}
}