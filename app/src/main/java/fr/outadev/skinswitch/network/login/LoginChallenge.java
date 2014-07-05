package fr.outadev.skinswitch.network.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a login challenge. When a user tries to log into minecraft.net
 * from a new device, he's asked to answer one of his secret questions. That's
 * the challenge.
 *
 * @author outadoc
 */
public class LoginChallenge {

	private String id;
	private String question;
	private String authToken;

	/**
	 * Creates a new login challenge.
	 *
	 * @param id        the ID of the question.
	 * @param question  the question to ask the user.
	 * @param authToken the authentication token that will be sent along with the
	 *                  answer.
	 */
	public LoginChallenge(String id, String question, String authToken) {
		this.id = id;
		this.question = question;
		this.authToken = authToken;
	}

	/**
	 * Creates a new login challenge by parsing the challenge web page.
	 *
	 * @param challengePage the page from which we will get the info required to send the
	 *                      answer back. it's supposedly minecraft.net/challenge.
	 */
	public LoginChallenge(String challengePage) {
		Matcher matcher;

		Pattern patternId = Pattern.compile("<input type=\"hidden\" name=\"questionId\" value=\"([0-9]+)\"( /)?>");
		Pattern patternQuestion = Pattern.compile("<label for=\"answer\">(.*)</label>");
		Pattern patternAuthToken = Pattern.compile("<input type=\"hidden\" name=\"authenticityToken\" value=\"([0-9a-f]*)\">");

		matcher = patternId.matcher(challengePage);
		if(matcher.find()) {
			this.id = matcher.group(1);
		}

		matcher = patternQuestion.matcher(challengePage);
		if(matcher.find()) {
			this.question = matcher.group(1);
		}

		matcher = patternAuthToken.matcher(challengePage);
		if(matcher.find()) {
			this.authToken = matcher.group(1);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Override
	public String toString() {
		return "MojangLoginChallenge [id=" + id + ", question=" + question + "]";
	}

}