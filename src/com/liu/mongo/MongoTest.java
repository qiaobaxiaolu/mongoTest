package com.liu.mongo;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;

/**
 * @author 刘佳瑞
 *
 * @date 2018年3月21日
 */
public class MongoTest {
	/**
	 * 查询集合中的所有文档
	 * @throws UnknownHostException
	 */
	@Test
	public void selectAll() throws UnknownHostException{
		Mongo mongo=new Mongo("localhost", 27017);
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("c1");
		DBCursor count = collection.find();
		int i=0;
		while (count.hasNext()) {
			DBObject next = count.next();
			System.err.println(next);
			i++;
			if (i>100) {
				break;
			}
		}
		mongo.close();
	}
	/**
	 * 插入
	 * @throws UnknownHostException
	 */
	@Test
	public void insert() throws UnknownHostException{
		Mongo mongo=new Mongo("localhost", 27017);
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("c1");
		DBObject object=new BasicDBObject();
		object.put("age", "123");
		collection.insert(object);
		mongo.close();
	}
	/**
	 * 删除
	 * @throws UnknownHostException
	 */
	@Test
	public void del() throws UnknownHostException{
		Mongo mongo=new Mongo("localhost", 27017);
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("c1");
		BasicDBObject o = new BasicDBObject("_id", new ObjectId("51b2f13c9f7c0d9e8a52a622"));
		collection.remove(o);
		mongo.close();
	}
	
		//更新集合中的文档
		@Test
		public void update() throws Exception{
			Mongo mongo = new Mongo("localhost",27017);
			DB db = mongo.getDB("test");
			
			DBCollection person = db.getCollection("c1");
			BasicDBObject query = new BasicDBObject("_id",new ObjectId("5ab07aa46042bc1dfdfb44d5"));
			DBObject one = person.findOne(query);
			one.put("name", "wangwu");
			WriteResult update = person.update(query, one);
			System.out.println(update.getN());
			//person.update(q, o);
			mongo.close();
		}
	
	
	
}
