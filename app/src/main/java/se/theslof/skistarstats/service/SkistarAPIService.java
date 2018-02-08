package se.theslof.skistarstats.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import se.theslof.skistarstats.model.Latest;
import se.theslof.skistarstats.model.Leaderboard;
import se.theslof.skistarstats.model.LiftRide;
import se.theslof.skistarstats.model.SkistarSummary;

/**
 * Created by theslof on 2018-01-30.
 */

public interface SkistarAPIService {

    @GET("statistic/summary")
    Call<SkistarSummary> summary(@Header("DisplayedEntityId") String skierId);

    @GET("friend/count")
    Call<Integer> friendCount(@Header("DisplayedEntityId") String skierId);

    @GET("statistic/latest")
    Call<Latest> latestStatistics(@Header("DisplayedEntityId") String skierId);

    @GET("statistic/rides")
    Call<List<LiftRide>> liftRides(@Header("DisplayedEntityId") String skierId, @Query("seasonId") String seasonId);

    @GET("leaderboard/friends/latest")
    Call<Leaderboard> leaderboard(@Header("DisplayedEntityId") String skierId);
}
/*
https://www.skistar.com/myskistar/game/api/v3/statistic/rides?seasonId=13
https://www.skistar.com/myskistar/game/api/v3/pins/latest?seasonId=13
https://www.skistar.com/myskistar/game/api/v3/statistic/active
https://www.skistar.com/myskistar/game/api/v3/statistic/summary
https://www.skistar.com/myskistar/game/api/v3/statistic/latest
https://www.skistar.com/myskistar/game/api/v3/friend/count
https://www.skistar.com/myskistar/game/api/v3/position
https://www.skistar.com/myskistar/game/api/v3/position/friend
https://www.skistar.com/myskistar/game/api/v3/leaderboard/friends/latest
*/