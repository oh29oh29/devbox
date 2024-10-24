# sorted Set
 
> score 값에 따라 정렬되는 고유한 문자열의 집합

> 순서를 갖는 자료구조이므로 index를 이용해 아이템에 접근할 수 있다.
> 
> index를 이용해 아이템에 접근할 일이 많다면 list가 아닌 sorted set를 사용하는 것도 방법이다.
> 
> index를 이용해 데이터에 접근할 때 list는 O(n), sorted set은 O(log(n))

> index로 데이터 조회: ZRANGE command는 기본적으로 index 기반으로 데이터를 조회한다.
>
> score로 데이터 조회: ZRANGE command에 BYSCORE 옵션을 사용하면 score를 이용해 데이터를 조회한다.
>
> 사전순으로 데이터 조회: ZRANGE command에 BYLEX 옵션을 사욯아면 사전순으로 데이터를 조회한다.  