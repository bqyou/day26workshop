package tfip.nus.iss.Day26.repo;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepo {

    @Autowired
    private MongoTemplate template;

    public List<Document> listGames(Integer limit, Integer offset) {
        Criteria c = Criteria.where("name").exists(true);

        Query q = Query.query(c)
                .limit(limit).skip(offset);

        q.fields().exclude("_id").include("gid", "name");

        return template.find(q, Document.class, "game");
    }

    public Long countGames() {
        Criteria c = Criteria.where("name").exists(true);
        Query count = Query.query(c);
        Long total = template.count(count, "game");
        return total;
    }

    public List<Document> listGamesByRank(Integer limit, Integer offset) {
        Criteria c = Criteria.where("name").exists(true);

        Query q = Query.query(c)
                .with(Sort.by(Sort.Direction.ASC, "ranking"))
                .limit(limit).skip(offset);

        q.fields().exclude("_id").include("gid", "name");

        return template.find(q, Document.class, "game");
    }

}
