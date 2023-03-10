package teste.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;


import br.com.project.report.util.DateUtils;

public class TesteData {

	@Test
	public void testData() {
		
		try {
			assertEquals("16012023", DateUtils.getDateAtualReportName());
			assertEquals("'2023-01-16'", DateUtils.formatDateSql(Calendar.getInstance().getTime()));
			assertEquals("2023-01-16", DateUtils.formatDateSqlSimple(Calendar.getInstance().getTime()));
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
		
		
		
		
	}

}
