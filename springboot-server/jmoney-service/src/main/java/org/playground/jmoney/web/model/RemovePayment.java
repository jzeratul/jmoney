package org.playground.jmoney.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO move to swagger
@Builder(toBuilder = true)
public class RemovePayment {

  private long jarId;
	private long paymentId;

}
