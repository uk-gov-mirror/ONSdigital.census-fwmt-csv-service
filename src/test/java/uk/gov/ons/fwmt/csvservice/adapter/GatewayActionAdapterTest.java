package uk.gov.ons.fwmt.csvservice.adapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.ons.census.fwmt.canonical.v1.CreateFieldWorkerJobRequest;
import uk.gov.ons.census.fwmt.common.error.GatewayException;
import uk.gov.ons.census.fwmt.csvservice.adapter.GatewayActionAdapter;
import uk.gov.ons.census.fwmt.csvservice.message.GatewayActionProducer;
import uk.gov.ons.census.fwmt.events.component.GatewayEventManager;
import uk.gov.ons.fwmt.csvservice.helper.FieldWorkerRequestMessageBuilder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static uk.gov.ons.census.fwmt.csvservice.implementation.ce.CEGatewayEventsConfig.CANONICAL_CE_CREATE_SENT;

@RunWith(MockitoJUnitRunner.class)
public class GatewayActionAdapterTest {

  @InjectMocks
  private GatewayActionAdapter gatewayActionAdapter;

  @Mock
  private GatewayEventManager gatewayEventManager;

  @Mock
  private GatewayActionProducer gatewayActionProducer;

  @Test
  public void sendCERequestToJobService() throws GatewayException {
    // Given
    CreateFieldWorkerJobRequest createJobRequest = new FieldWorkerRequestMessageBuilder()
        .buildCreateFieldWorkerJobRequestCE();

    // When
    gatewayActionAdapter.sendJobRequest(createJobRequest, CANONICAL_CE_CREATE_SENT);

    // Then
    Mockito.verify(gatewayEventManager).triggerEvent(anyString(), eq(CANONICAL_CE_CREATE_SENT));
  }
}
