package service;

import mapper.ScoreMapper;
import mapper.UserMapper;
import pojo.Score;
import pojo.User;

public class ScoreService {
    private ScoreMapper scoreMapper;
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }
    //查看个人积分
    public Score queryScore(Integer u_id)throws Exception{
        User user=userMapper.querryUserById(u_id);
        if(user!=null){
            return scoreMapper.querryScoreByUid(u_id);
        }
        return null;
    }
    //添加积分
    public boolean addScore(Score score)throws Exception{
        Score score1=scoreMapper.querryScoreByUid(score.getU_id());
        if(score1==null){
            scoreMapper.addScore(score);
            return true;
        }
        return false;
    }
    //修改积分
    public boolean updateScore(Score score)throws Exception{
        Score score1=scoreMapper.querryScoreByUid(score.getU_id());
        if(score1!=null){
            scoreMapper.updateScore(score);
            return true;

        }
        return false;
    }
}
