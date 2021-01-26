package com.fm_takehome.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fm_takehome.models.StopAttributes;
import com.fm_takehome.services.StopAttributesService;

import junit.framework.Assert;

public class StopAttributesRepositoryTest {
	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class BookServiceUnitTest {

	    @Autowired
	    private StopAttributesService stopAttributesService;

	    @Test
	    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
	        List<StopAttributes> stopAttributes = stopAttributesService.list();

	        Assert.assertEquals(stopAttributes.size(), 3);
	    }
	}
}
