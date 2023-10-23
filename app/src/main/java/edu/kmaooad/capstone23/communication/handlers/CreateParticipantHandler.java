package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.communication.services.ParticipantService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class CreateParticipantHandler implements CommandHandler<CreateParticipant, ParticipantCreated> {
  @Inject
  ParticipantService participantService;

  private Participant participant;

  @Override
  public Result<ParticipantCreated> handle(CreateParticipant command) {
    String chatId = command.getChatId();
    String userId = command.getUserId();

    boolean canCreateParticipant = participantService.validateChatAndUser(chatId, userId);

    if (!canCreateParticipant) {
      return new Result<ParticipantCreated>(ErrorCode.EXCEPTION, "Chat or user do not exist");
    }

    initParticipant(chatId, userId);

    this.participantService.insert(participant);

    ParticipantCreated createdParticipant = new ParticipantCreated(participant.id.toHexString());

    return new Result<ParticipantCreated>(createdParticipant);
  }

  private void initParticipant(String chatId, String userId) {
    participant = new Participant();

    participant.chatId = new ObjectId(chatId);
    participant.userId = new ObjectId(userId);
  }
}
