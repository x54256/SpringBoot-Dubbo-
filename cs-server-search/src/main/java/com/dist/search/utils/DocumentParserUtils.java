package com.dist.search.utils;

import com.dist.base.utils.IdUtil;
import com.dist.model.search.dto.DocumentDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wsw
 * @Date 2019/1/10
 */
public abstract class DocumentParserUtils {

//    /**
//     * @throws Exception
//     */
//    public static void parseDOC() throws Exception {
//        BodyContentHandler handler = new BodyContentHandler();
//        Metadata metadata = new Metadata();
//        FileInputStream inputstream = new FileInputStream(new File("C:\\wsw\\2018.doc"));
//        ParseContext pcontext = new ParseContext();
//
//        Parser parser = new AutoDetectParser();
//        pcontext.set(Parser.class, parser);
//        parser.parse(inputstream, handler, metadata, pcontext);
//        System.out.println("Content：" + handler.toString());
//        for (String name : metadata.names()) {
//            System.out.println(name);
//        }
//    }
//
//    /**
//     * 解析excel
//     *
//     * @return
//     * @throws Exception
//     */
//    public static List<DocumentDTO> parseXlsx() throws Exception {
//        List<DocumentDTO> resultList = new ArrayList<>();
//        BodyContentHandler handler = new BodyContentHandler();
//        Metadata metadata = new Metadata();
//        FileInputStream fileInputStream = new FileInputStream(new File("c:\\wsw\\2019.xlsx"));
//        ParseContext parseContext = new ParseContext();
//
//        //OOXml parser
//        OOXMLParser ooxmlParser = new OOXMLParser();
//        ooxmlParser.parse(fileInputStream, handler, metadata, parseContext);
//        String content = handler.toString();
//
//        return resultList;
//    }
//
//    /**
//     * 解析 txt 文本
//     *
//     * @return
//     * @throws Exception
//     */
//    public static List<DocumentDTO> parseTxt(Long reviewTaskId) throws Exception {
//        List<DocumentDTO> resultList = new ArrayList<>();
//        BodyContentHandler handler = new BodyContentHandler();
//        Metadata metadata = new Metadata();
//        FileInputStream fileInputStream = new FileInputStream(new File("C:\\wsw\\b.txt"));
//        ParseContext parseContext = new ParseContext();
//        TXTParser TexTParser = new TXTParser();
//        TexTParser.parse(fileInputStream, handler, metadata, parseContext);
//        String content = handler.toString();
//        if ("".equals(content.trim())) {
//            return resultList;
//        }
//        String uuid = IdUtil.getGlobalId();
//        DocumentDTO entity = createDocumentEntity(uuid, uuid, reviewTaskId, content, 1);
//        resultList.add(entity);
//        return resultList;
//    }

    public static List<DocumentDTO> parsePDF(String pdfFilePath) throws Exception {
        RandomAccessRead accessRead = new RandomAccessFile(new File(pdfFilePath), "r");
        // 创建PDF解析器
        PDFParser parser = new PDFParser(accessRead);
        // 执行PDF解析过程
        parser.parse();
        // 获取解析器的PDF文档对象
        PDDocument pdfdocument = parser.getPDDocument();
        // 生成PDF文档内容剥离器
        PDFTextStripper pdfstripper = new PDFTextStripper();
        // 获取总页数
        int count = pdfdocument.getPages().getCount();
        // 生成文件uuid
        String fileId = IdUtil.getGlobalId();
        List<DocumentDTO> resultList = new ArrayList<>();
        for (int index = 1; index <= count; index++) {
            // 获取每一页
            pdfstripper.setStartPage(index);
            pdfstripper.setEndPage(index);
            String content = pdfstripper.getText(pdfdocument);
            if (StringUtils.isBlank(content)) {
                continue;
            }
            DocumentDTO entity = createDocumentDTO(IdUtil.getGlobalId(), fileId, content, index);
            resultList.add(entity);
        }
        return resultList;
    }

    private static DocumentDTO createDocumentDTO(String id, String fileId, String content, int pageIndex) {

        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setId(id);
        documentDTO.setFileId(fileId);
        documentDTO.setContent(content);
        documentDTO.setPageIndex(pageIndex);

        return documentDTO;
    }
}
