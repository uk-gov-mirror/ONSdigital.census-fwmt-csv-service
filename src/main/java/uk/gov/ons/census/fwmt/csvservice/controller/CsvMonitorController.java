package uk.gov.ons.census.fwmt.csvservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uk.gov.ons.census.fwmt.common.error.GatewayException;
import uk.gov.ons.census.fwmt.csvservice.service.CSVConverterService;

import java.util.Map;

@Controller
public class CsvMonitorController {

  @Autowired
  private Map<String, CSVConverterService> csvServiceMap;

  @GetMapping("/ingestCeCsvFile")
  public ResponseEntity<String> ingestCeCsvFile() throws GatewayException {
    final CSVConverterService ceConverterService = csvServiceMap.get("CE");
    ceConverterService.convertToCanonical();
    return ResponseEntity.ok("CE adapter service activated");
  }

  @GetMapping("/ingestCCSCsvFile")
  public ResponseEntity<String> ingestCSSCsvFile() throws GatewayException {
    final CSVConverterService ccsConverterService = csvServiceMap.get("CCS");
    ccsConverterService.convertToCanonical();
    return ResponseEntity.ok("CCS adapter service activated");
  }

  @GetMapping("/ingestAddressCheckCsvFile")
  public ResponseEntity<String> ingestAddressCheckCsvFile() throws GatewayException {
    final CSVConverterService ccsConverterService = csvServiceMap.get("AC");
    ccsConverterService.convertToCanonical();
    return ResponseEntity.ok("AC adapter service activated");
  }
}