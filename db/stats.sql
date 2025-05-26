WITH date_series AS (SELECT GENERATE_SERIES(
                                    (SELECT MIN(answered_at)::timestamp FROM user_question_entity),
                                    (SELECT MAX(answered_at + INTERVAL '1 day')::timestamp FROM user_question_entity),
                                    INTERVAL '1 day'
                            )::date AS date)
SELECT TO_CHAR(date_series.date, 'DD.MM.YYYY')                                      AS day,

       -- Number of questions answered that day
       COALESCE(COUNT(user_question_entity.answered_at), 0)                         AS count,

       -- Number of unique players on that day
       COALESCE((SELECT COUNT(DISTINCT gs.player_id)
                 FROM game_session_entity gs
                 WHERE gs.id IN (SELECT game_session_id
                                 FROM user_question_entity
                                 WHERE answered_at::date = date_series.date)), 0)   AS anzahl_spieler,

       -- List of players with counts in format: username (xN)
       COALESCE((SELECT STRING_AGG(DISTINCT (
           p.username || ' (' ||
           COALESCE((SELECT COUNT(*)
                     FROM game_session_entity gs2
                              JOIN user_question_entity uqe2 ON uqe2.game_session_id = gs2.id
                     WHERE gs2.player_id = p.id
                       AND uqe2.answered_at::date = date_series.date), 0) || ')'
           ), ', ')
                 FROM game_session_entity gs
                          JOIN player_entity p ON p.id = gs.player_id
                 WHERE gs.id IN (SELECT game_session_id
                                 FROM user_question_entity
                                 WHERE answered_at::date = date_series.date)), '-') AS spieler

FROM date_series
         LEFT JOIN
     user_question_entity ON date_series.date = answered_at::date
GROUP BY date_series.date
ORDER BY date_series.date desc;

