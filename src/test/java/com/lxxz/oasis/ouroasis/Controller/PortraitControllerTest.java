package com.lxxz.oasis.ouroasis.Controller;

import com.lxxz.oasis.ouroasis.po.AuthorPortrait;
import com.lxxz.oasis.ouroasis.vo.ResponseVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration

public class PortraitControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    PortraitController portraitController;

    @Test
    public void searchAuthorPortraitsTest(){
        ResponseVO ans = portraitController.searchAuthorPortraits("A.");
        System.out.println(1);
    }

    @Before
    public void setUp() throws Exception{
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void searchPortraitByAffiliation() throws Exception {
        mockMvc.perform(get("/searchPortrait/affiliation").param("affiliation","Nanjing University"))
                .andExpect(status().isOk());
    }

    @Test
    public void searchPortraitByAuthor() throws Exception {
        mockMvc.perform(get("/searchPortrait/author").param("authorName","J. Sun"))
                .andExpect(status().isOk());
    }

}