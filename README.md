# 住所情報検索API

## 起動

### Docker

```sh
# ビルドしなおしたら、--no-cache をつける
docker-compose build
docker-compose up -d
```

### 住所情報取り込みバッチの実行

```sh
java -jar batch/target/batch-0.0.1-SNAPSHOT.jar inputFile=/path/to/latest.txt
```
