/**
 * 
 */
package com.clementscode.mmi.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.clementscode.mmi.data.SessionDataCollector.RespType;
import com.clementscode.mmi.res.CategoryItem;

/**
 * @author bclement
 * 
 */
public class SessionDataCollectorTest {

	protected static final String session = "session";

	protected static final String desc = "description";

	protected final File tmp;

	protected final File tmp1;

	protected final CategoryItem item1;

	protected CategoryItem item2;

	/**
	 * @throws IOException
	 * 
	 */
	public SessionDataCollectorTest() throws IOException {
		tmp = File.createTempFile("foo", "bar");
		tmp1 = File.createTempFile("foo", "bar");
		item1 = new CategoryItem(tmp, tmp);
		item2 = new CategoryItem(tmp1, tmp1);
	}

	@Test
	public void percentTest() {
		SessionDataCollector collector = new SessionDataCollector(session, desc);
		collector.addResponse(item1, true, RespType.INDEPENDANT);
		collector.addResponse(item1, false, RespType.MODEL);
		collector.addResponse(item2, true, RespType.VERBAL);
		collector.addResponse(item2, false, RespType.NONE);
		SessionData data = collector.getData();
		assertNotNull(data);
		assertEquals(25, data.getPercentIndep(), 0);
		assertEquals(25, data.getPercentVerbal(), 0);
		assertEquals(25, data.getPercentModel(), 0);
		assertEquals(session, data.getName());
		assertEquals(desc, data.getDescription());
	}
}
