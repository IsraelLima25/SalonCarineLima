package br.com.salon.carine.lima.util;

public interface Messenger {

	public void emitterMessagesuccess(String text);

	public void emitterMessagedanger(String text);

	public void emitterMessageinfo(String text);

	public void emitterMessagewarning(String text);

}
