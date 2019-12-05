package test;

import java.io.File;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class Test1 {
	String[] a = {
			"3, 华为 - 华为电脑, 爆款",
			"4, 华为手机, 旗舰",
			"5, 联想 - Thinkpad, 商务本",
			"6, 联想手机, 自拍神器"
	};
	
	@Test
	public void test1() throws Exception {
		//存储索引文件的路径 D:\xitong\lucene-slor\abc
		File path = new File("D:/xitong/lucene-slor/abc");
		FSDirectory d = FSDirectory.open(path.toPath());
		//lucene提供的中文分词器
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		//通过配置对象来指定分词器
		IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
		//索引输出工具
		IndexWriter writer = new IndexWriter(d, cfg);
		
		for (int i = 0; i < a.length; i++) {
			String[] strs = a[i].split(",");
			
			//创建文档,文档中包含的是要索引的字段
			Document doc = new Document();
			doc.add(new LongPoint("id", Long.parseLong(strs[0])));
			doc.add(new StoredField("id", Long.parseLong(strs[0])));
			doc.add(new TextField("title", strs[1], Store.YES));
			doc.add(new TextField("sellPoint", strs[2], Store.YES));
			
			//将文档写入磁盘索引文件
			writer.addDocument(doc);
		}
		writer.close();
		
	}
	
}