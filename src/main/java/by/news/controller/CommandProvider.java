package by.news.controller;

import java.util.HashMap;
import java.util.Map;

import by.news.controller.impl.ChangeLocale;
import by.news.controller.impl.GoToAuthorization;
import by.news.controller.impl.GoToMainPage;
import by.news.controller.impl.GoToRegistration;
import by.news.controller.impl.RegistrationNewUser;
import by.news.controller.impl.SignIn;
import by.news.controller.impl.UnknownCommand;

public class CommandProvider {
	private static final Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new GoToAuthorization());
		commands.put(CommandName.REGISTRATION, new GoToRegistration());
		commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
		commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
	}

	public Command findCommand(String nameOfCommand) {
		if (nameOfCommand == null) {
			nameOfCommand = CommandName.UNKNOWN_COMMAND.toString();
		}
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(nameOfCommand.toUpperCase());
		} catch (IllegalArgumentException e) {
			// logging
			commandName = CommandName.UNKNOWN_COMMAND;
		}
		return commands.get(commandName);
	}
}
