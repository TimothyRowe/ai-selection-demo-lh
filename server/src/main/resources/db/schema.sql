CREATE DATABASE IF NOT EXISTS smart_selection DEFAULT CHARSET utf8mb4;
USE smart_selection;

-- market_data_snapshot: 卖家精灵定期同步的市场数据
CREATE TABLE market_data_snapshot (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  asin VARCHAR(20) NOT NULL,
  category_id VARCHAR(50),
  category_name VARCHAR(200),
  price DECIMAL(10,2),
  bsr_rank INT,
  monthly_sales INT,
  revenue DECIMAL(12,2),
  rating DECIMAL(2,1),
  review_count INT,
  seller_count INT,
  fba_ratio DECIMAL(4,2),
  listing_date DATE,
  growth_rate_monthly DECIMAL(6,2),
  growth_rate_quarterly DECIMAL(6,2),
  snapshot_date DATE NOT NULL,
  country_code VARCHAR(5) NOT NULL DEFAULT 'US',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_asin (asin),
  INDEX idx_category (category_id),
  INDEX idx_snapshot_date (snapshot_date),
  INDEX idx_country_bsr (country_code, bsr_rank)
);

-- selection_scheme: 筛选方案
CREATE TABLE selection_scheme (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(500),
  conditions_json JSON NOT NULL,
  visibility VARCHAR(20) NOT NULL DEFAULT 'private' COMMENT 'private/team/global',
  creator_id BIGINT NOT NULL,
  team_id BIGINT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT DEFAULT 0
);

-- selection_pool: 结果池
CREATE TABLE selection_pool (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  asin VARCHAR(20) NOT NULL,
  country_code VARCHAR(5) NOT NULL DEFAULT 'US',
  source VARCHAR(20) NOT NULL COMMENT 'manual/ai/anomaly',
  source_scheme_id BIGINT,
  status VARCHAR(20) NOT NULL DEFAULT 'pending' COMMENT 'pending/evaluating/approved/rejected',
  evaluator_id BIGINT,
  evaluate_time DATETIME,
  reject_reason VARCHAR(500),
  market_snapshot_id BIGINT,
  erp_product_id BIGINT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX idx_status (status),
  INDEX idx_asin_country (asin, country_code),
  INDEX idx_evaluator (evaluator_id)
);

-- selection_log: 筛选执行日志
CREATE TABLE selection_log (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  scheme_id BIGINT NOT NULL,
  executor_id BIGINT NOT NULL,
  conditions_json JSON,
  result_count INT DEFAULT 0,
  pool_added_count INT DEFAULT 0,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
