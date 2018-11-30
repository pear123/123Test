package mapper;

import pojo.Score;

public interface ScoreMapper {
    public void  addScore(Score score)throws Exception;
    public void updateScore(Score score)throws Exception;
    public Score querryScoreByUid(Integer u_id)throws Exception;
    public Score querryScoreById(Integer id)throws Exception;
}
