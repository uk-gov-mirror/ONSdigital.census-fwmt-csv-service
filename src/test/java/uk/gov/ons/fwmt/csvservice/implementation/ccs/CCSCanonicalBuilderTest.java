package uk.gov.ons.fwmt.csvservice.implementation.ccs;

import org.junit.Test;
import uk.gov.ons.census.fwmt.canonical.v1.CreateFieldWorkerJobRequest;
import uk.gov.ons.census.fwmt.csvservice.dto.CCSPropertyListing;
import uk.gov.ons.fwmt.csvservice.helper.CSVRecordDTOBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static uk.gov.ons.census.fwmt.csvservice.implementation.ccs.CCSCanonicalBuilder.createCCSJob;

public class CCSCanonicalBuilderTest {

  @Test
  public void createCCS () {
    // Given
    CCSPropertyListing ccsPropertyListing = new CSVRecordDTOBuilder().createCCSCSVRecord();

    // When
    CreateFieldWorkerJobRequest createFieldWorkerJobRequest = createCCSJob(ccsPropertyListing);

    // Then
    assertNotNull(createFieldWorkerJobRequest.getCaseId());
    assertEquals(ccsPropertyListing.getPostCode(), createFieldWorkerJobRequest.getAddress().getPostCode());
    assertEquals(ccsPropertyListing.getLatitude(), createFieldWorkerJobRequest.getAddress().getLatitude());
    assertEquals(ccsPropertyListing.getCoordinatorId(), createFieldWorkerJobRequest.getCoordinatorId());
  }
}
