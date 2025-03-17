package digitalhousemoney.servicio_tarjetas.application.service;


import digitalhousemoney.servicio_tarjetas.application.port.CardRepository;
import digitalhousemoney.servicio_tarjetas.domain.models.Card;
import digitalhousemoney.servicio_tarjetas.exception.CardAlreadyExistsException;
import digitalhousemoney.servicio_tarjetas.exception.ResourceNotFoundException;
import digitalhousemoney.servicio_tarjetas.infrastructure.feign.UserClient;
import digitalhousemoney.servicio_tarjetas.infrastructure.web.request.CardDTO;
import digitalhousemoney.servicio_tarjetas.infrastructure.web.response.CardCreateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final UserClient userClient;

    public CardService(CardRepository cardRepository, UserClient userClient) {
        this.cardRepository = cardRepository;
        this.userClient = userClient;
    }

    public List<CardDTO> getCardsByUserId(Long userId) {
        if (!userClient.userExists(userId)) {
            throw new RuntimeException("User not found for userId " + userId);
        }

        List<Card> cards = cardRepository.findAllByUserId(userId);
        return cards.stream().map(CardDTO::new).collect(Collectors.toList());
    }

    public CardDTO getCardById(Long userId, Long cardId) {
        if (!userClient.userExists(userId)) {
            throw new RuntimeException("User not found for userId " + userId);
        }

        Card card = cardRepository.findByIdAndUserId(cardId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for cardId " + cardId + " and userId " + userId));

        return new CardDTO(card);
    }

    public CardDTO createCardForUser(Long userId, CardCreateDTO cardCreateDTO) {
        Optional<Card> existingCard = cardRepository.findByNumber(cardCreateDTO.getNumber());
        if (existingCard.isPresent()) {
            throw new CardAlreadyExistsException("Card with number " + cardCreateDTO.getNumber() + " already exists.");
        }

        Card card = new Card(cardCreateDTO);
        card.setUserId(userId); // Set the userId for the card
        Card savedCard = cardRepository.save(card);

        return new CardDTO(savedCard);
    }

    public void deleteCardForUser(Long userId, Long cardId) {
        if (!cardRepository.existsByIdAndUserId(cardId, userId)) {
            throw new ResourceNotFoundException("Card not found for cardId " + cardId + " and userId " + userId);
        }

        cardRepository.deleteById(cardId);
    }

    public CardDTO getCardByNumber(Long userId, String cardNumberSuffix) {
        System.out.println("Card Number Suffix: " + cardNumberSuffix); // Verificar el sufijo
        if (!userClient.userExists(userId)) {
            throw new RuntimeException("User not found for userId " + userId);
        }

        // Busca la tarjeta por el sufijo (últimos 4 dígitos)
        Card card = cardRepository.findByNumberEndingWith(cardNumberSuffix)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for number ending with " + cardNumberSuffix + " and userId " + userId));

        return new CardDTO(card);
    }
}
