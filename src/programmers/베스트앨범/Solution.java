package programmers.베스트앨범;

import java.util.*;

class Solution {
    HashMap<String, Integer> genreMap = new HashMap<>();
    HashMap<String, PriorityQueue<Song>> songMap = new HashMap<String, PriorityQueue<Song>>();
    ArrayList<Song> songs = new ArrayList<>();

    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        int songCnt = genres.length;

        for(int i = 0 ; i < songCnt; i++){
            String genre = genres[i];
            genreMap.putIfAbsent(genre, 0);
            int cnt = genreMap.get(genre) + plays[i];
            genreMap.put(genre, cnt);
        } // 장르별 곡의 play 횟수 구하기

        for(int i = 0 ; i < songCnt; i++){
            int id = i;
            int playCnt = plays[i];
            int genrePlayCnt = genreMap.get(genres[i]);

            String genre = genres[i];
            Song song = new Song(id, playCnt, genrePlayCnt, genre);

            // 하나의 새로운 곡을 만들고

            songMap.putIfAbsent(song.genre, new PriorityQueue<Song>(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return -Integer.compare(o1.playCnt, o2.playCnt);
                }
            }));
            // 해당 곡은 HashMap의 PriorityQueue에 저장된다. 아때, queue의 front에는 재생 횟수가 가장 높은 놈이 존재한다.

            songMap.get(song.genre).offer(song);
        } // Song 객체 정의

        // 중간점검 - 1
//        for (Map.Entry<String, PriorityQueue<Song>> entry : songMap.entrySet()) {
//            System.out.println(entry.getKey());
//            Queue<Song> q = entry.getValue();
//            while(!q.isEmpty()){
//                System.out.println(q.poll() + " ");
//            }
//        }

        // 각각의 장르별 Song들 중에서 장르별로 재생 횟수가 가장 높은 2개의 Song들을 골라야 한다.
        for (Map.Entry<String, PriorityQueue<Song>> entry : songMap.entrySet()) {
            Queue<Song> q = entry.getValue();
            int cnt = 0;
            while(!(q.isEmpty() || cnt == 2)){
                Song song = q.poll();
                songs.add(song);
                cnt++;
            }
        }

        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                int genreCnt1 = o1.genrePlayCnt;
                int genreCnt2 = o2.genrePlayCnt;

                if(genreCnt1 == genreCnt2){

                    int playCnt1 = o1.playCnt;
                    int playCnt2 = o2.playCnt;

                    if(playCnt1 == playCnt2){
                        if(o1.id == o2.id){
                            return 0;
                        }else{
                            return Integer.compare(o1.id, o2.id);
                        }
                    }else{
                        return -Integer.compare(playCnt1, playCnt2);
                    }
                }else {
                    return -Integer.compare(genreCnt1,genreCnt2);
                }
            }
        });

        answer = new int[songs.size()];

        int idx = 0;
        for (Song song : songs) {
            answer[idx++] = song.id;
        }

        return answer;
    }

    class Song{
        int id;
        int playCnt;
        int genrePlayCnt;
        String genre;

        public Song(int id, int playCnt, int genrePlayCnt, String genre) {
            this.id = id;
            this.playCnt = playCnt;
            this.genrePlayCnt = genrePlayCnt;
            this.genre = genre;
        }

        @Override
        public String toString() {
            return "Song{" +
                    "id=" + id +
                    ", playCnt=" + playCnt +
                    ", genrePlayCnt=" + genrePlayCnt +
                    ", genre='" + genre + '\'' +
                    '}';
        }
    }
}
