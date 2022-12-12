package br.com.banco.service.rest;

import br.com.banco.config.AppConfig;
import br.com.banco.dto.ClienteDTO;
import br.com.banco.exceptions.ClienteServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ClienteRestService {
    private final AppConfig appConfig;

    public ClienteDTO consultarClientePorNumeroConta(Long numeroConta) throws ClienteServiceException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ClienteDTO> response = restTemplate.getForEntity(appConfig.getModuloClienteBaseUrl()
                    .concat("consulta/").concat(String.valueOf(numeroConta)), ClienteDTO.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (HttpClientErrorException e) {
            throw new ClienteServiceException(e.getMessage());
        }
        throw new ClienteServiceException("Ocorreu um erro ao chamar a api de clientes");
    }
}
