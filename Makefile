# 定義變數
MVN = ./mvnw
JAVA = java
JAR_FILE = target/coffee-pos-0.0.1-SNAPSHOT.jar

# 默認目標
.PHONY: all
all: clean build run

# 清理專案
.PHONY: clean
clean:
	$(MVN) clean

# 打包專案
.PHONY: build
build:
	$(MVN) package

# 啟動專案
.PHONY: run
run:
	$(JAVA) -jar $(JAR_FILE)

# 開發模式
.PHONY: dev
dev:
	$(MVN) spring-boot:run

# flyway 遷移
.PHONY: migrate
migrate:
	$(MVN) flyway:migrate

# 停止專案（如果你使用了背景進程）
.PHONY: stop
stop:
	@pkill -f '$(JAR_FILE)'

# 重新啟動專案
.PHONY: restart
restart: stop run

# 安裝依賴庫
.PHONY: install
install:
	$(MVN) dependency:resolve

# spotless:apply
.PHONY: format
format:
	${MVN} spotless:apply