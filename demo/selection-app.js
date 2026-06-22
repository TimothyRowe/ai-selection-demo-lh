const mockProducts = [
    {
        img: "https://m.media-amazon.com/images/I/71oR8dKttSL._AC_SL1500_.jpg",
        title: "DEWALT 20V MAX Cordless Drill Combo Kit, 2-Tool",
        asin: "B0D1KXKH7Y", price: 139.00, bsr: 342, sales: 12500, revenue: 1737500,
        rating: 4.7, reviews: 28450, date: "2024-03-15", fulfillment: "FBA", growth: 18.5,
        risk: "high", riskDetail: "外观专利相似度92%（US D945,XXX）", certs: ["UL", "FCC"], certsOwned: true
    },
    {
        img: "https://m.media-amazon.com/images/I/71Gu-WKKxuL._AC_SL1500_.jpg",
        title: "BLACK+DECKER 20V MAX Drill/Driver with 30pc Accessories",
        asin: "B08BKQY9FG", price: 49.99, bsr: 1205, sales: 5800, revenue: 289942,
        rating: 4.5, reviews: 42100, date: "2020-08-10", fulfillment: "FBA", growth: 5.2,
        risk: "low", riskDetail: "未检测到侵权风险", certs: ["UL", "FCC"], certsOwned: true
    },
    {
        img: "https://m.media-amazon.com/images/I/61HaSLguSxL._AC_SL1500_.jpg",
        title: "Bosch 12V Flexiclick 5-In-1 Drill/Driver System",
        asin: "B0BN8MZLKR", price: 149.00, bsr: 2847, sales: 2100, revenue: 312900,
        rating: 4.6, reviews: 1890, date: "2023-01-20", fulfillment: "FBA", growth: 12.8,
        risk: "medium", riskDetail: "外观相似度68%，需人工复核", certs: ["CE", "RoHS"], certsOwned: true
    },
    {
        img: "https://m.media-amazon.com/images/I/71Vy3K4MjQL._AC_SL1500_.jpg",
        title: "KIMO Cordless Drill Set 20V with 2 Batteries & 68pcs",
        asin: "B0CG9LQSHG", price: 59.99, bsr: 4521, sales: 3200, revenue: 191968,
        rating: 4.3, reviews: 2450, date: "2023-09-05", fulfillment: "FBA", growth: 25.3,
        risk: "low", riskDetail: "未检测到侵权风险", certs: ["UL"], certsOwned: true
    },
    {
        img: "https://m.media-amazon.com/images/I/71Gu+QFMRSL._AC_SL1500_.jpg",
        title: "Milwaukee M18 FUEL 1/2 in. Hammer Drill Driver",
        asin: "B0BYM4JCK1", price: 199.00, bsr: 567, sales: 8900, revenue: 1771100,
        rating: 4.8, reviews: 15200, date: "2023-06-12", fulfillment: "FBA", growth: 8.9,
        risk: "high", riskDetail: "品牌违禁库命中：Milwaukee为强知产保护品牌", certs: ["UL", "FCC", "UN38.3"], certsOwned: false
    },
    {
        img: "https://m.media-amazon.com/images/I/61g+McQpg7L._AC_SL1500_.jpg",
        title: "HYCHIKA 20V Cordless Drill Driver Set with 2.0Ah Battery",
        asin: "B0C8NP4KLR", price: 39.99, bsr: 3456, sales: 4200, revenue: 167958,
        rating: 4.1, reviews: 3890, date: "2022-11-18", fulfillment: "FBA", growth: -3.2,
        risk: "low", riskDetail: "未检测到侵权风险", certs: ["FCC"], certsOwned: true
    },
    {
        img: "https://m.media-amazon.com/images/I/71bR5KmJMDL._AC_SL1500_.jpg",
        title: "Makita XFD131 18V LXT Lithium-Ion Compact Driver-Drill Kit",
        asin: "B00WAQKKE2", price: 159.00, bsr: 1890, sales: 3500, revenue: 556500,
        rating: 4.7, reviews: 8900, date: "2021-04-22", fulfillment: "FBA", growth: 6.1,
        risk: "medium", riskDetail: "外观相似度55%，建议差异化设计", certs: ["UL", "FCC", "UN38.3"], certsOwned: false
    },
    {
        img: "https://m.media-amazon.com/images/I/71Vy3K4MjQL._AC_SL1500_.jpg",
        title: "WORKPRO 20V Cordless Drill Combo Kit, Drill Driver",
        asin: "B0DKM3R5TW", price: 89.99, bsr: 5678, sales: 1850, revenue: 166482,
        rating: 4.2, reviews: 567, date: "2025-02-08", fulfillment: "FBA", growth: 35.1,
        risk: "low", riskDetail: "未检测到侵权风险", certs: ["UL", "FCC"], certsOwned: true
    },
];

function renderTable() {
    const riskMap = {
        low: {label:'安全', cls:'ant-tag-green', icon:'bi-shield-check'},
        medium: {label:'需复核', cls:'ant-tag-orange', icon:'bi-shield-exclamation'},
        high: {label:'高风险', cls:'', icon:'bi-shield-x', style:'background:#fff2f0;color:#cf1322;border:1px solid #ffa39e;'}
    };
    const tbody = document.getElementById('productBody');
    tbody.innerHTML = mockProducts.map((p, i) => {
        const r = riskMap[p.risk];
        const riskStyle = r.style || '';
        const certTags = p.certs.map(c => `<span style="padding:1px 4px;border-radius:2px;font-size:10px;background:${p.certsOwned?'#f6ffed':'#fff7e6'};color:${p.certsOwned?'#389e0d':'#d46b08'};border:1px solid ${p.certsOwned?'#b7eb8f':'#ffd591'};margin-right:2px;">${c}</span>`).join('');
        return `
        <tr>
            <td><input type="checkbox"></td>
            <td>
                <div class="product-cell">
                    <img src="${p.img}" onclick="openModal()" title="点击查看详情">
                    <div>
                        <a href="https://www.amazon.com/dp/${p.asin}" target="_blank" class="product-title" title="${p.title}">${p.title}</a>
                        <div class="product-asin">${p.asin}</div>
                    </div>
                </div>
            </td>
            <td>$${p.price.toFixed(2)}</td>
            <td>#${p.bsr.toLocaleString()}</td>
            <td>${p.sales.toLocaleString()}</td>
            <td>$${(p.revenue/1000).toFixed(0)}K</td>
            <td>${p.rating} ⭐</td>
            <td>${p.reviews.toLocaleString()}</td>
            <td class="${p.growth >= 0 ? 'trend-up' : 'trend-down'}">${p.growth > 0 ? '+' : ''}${p.growth}%</td>
            <td>
                <button class="ant-btn ant-btn-sm" onclick="openModal()" title="查看详情"><i class="bi bi-eye"></i></button>
                <button class="ant-btn ant-btn-sm" style="color:var(--success);border-color:var(--success);" title="加入结果池"><i class="bi bi-plus"></i></button>
            </td>
        </tr>`;
    }).join('');
}

function doSearch() {
    document.getElementById('resultCount').textContent = Math.floor(Math.random() * 200 + 150);
    renderTable();
}

function openModal() {
    document.getElementById('detailModal').classList.add('show');
    document.body.style.overflow = 'hidden';
}

function closeModal() {
    document.getElementById('detailModal').classList.remove('show');
    document.body.style.overflow = '';
}

function switchTab(name, el) {
    document.querySelectorAll('.ant-tab').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.tab-panel').forEach(p => p.classList.remove('active'));
    el.classList.add('active');
    document.getElementById('panel-' + name).classList.add('active');
}

function selectPreset(el) {
    const wasActive = el.classList.contains('ant-tag-active');
    document.querySelectorAll('.ant-tag').forEach(t => t.classList.remove('ant-tag-active'));
    if (!wasActive) el.classList.add('ant-tag-active');
}

function resetFilters() {
    document.querySelectorAll('.ant-input, .range-group input').forEach(input => {
        if (input.type === 'number') input.value = '';
    });
}

function showPage(name) {
    document.querySelectorAll('#page-search, #page-pool, #page-ai-rules, #page-sourcing, #page-review-mining, #page-painpoint, #page-trend-radar').forEach(p => p.style.display = 'none');
    document.getElementById('page-' + name).style.display = '';
    document.querySelectorAll('.menu-item').forEach(m => m.classList.remove('active'));
    event.currentTarget.classList.add('active');
}

function openAnomalyModal() {
    document.getElementById('anomalyModal').classList.add('show');
    document.body.style.overflow = 'hidden';
}

function switchInsightTab(name, el) {
    document.querySelectorAll('.insight-tab').forEach(t => {
        t.style.borderBottomColor = 'transparent';
        t.style.color = 'var(--text-secondary)';
    });
    el.style.borderBottomColor = 'var(--primary)';
    el.style.color = 'var(--primary)';
    ['anomaly','bsr','listing','manage'].forEach(id => {
        const panel = document.getElementById('insight-panel-' + id);
        if (panel) panel.style.display = id === name ? '' : 'none';
    });
}

function toggleColumnPicker() {
    const picker = document.getElementById('columnPicker');
    picker.style.display = picker.style.display === 'none' ? '' : 'none';
}

function doPush(btn) {
    const row = btn.closest('tr');
    const statusCell = row.querySelector('td:nth-last-child(2)');
    const random = Math.random();
    if (random > 0.3) {
        statusCell.innerHTML = '<span style="padding:2px 8px;border-radius:3px;font-size:11px;background:#f6ffed;color:#389e0d;border:1px solid #b7eb8f;">推送成功</span>';
        showToast('推送成功：已推送至ERP产品库', 'success');
    } else {
        statusCell.innerHTML = '<span style="padding:2px 8px;border-radius:3px;font-size:11px;background:#fff2f0;color:#cf1322;border:1px solid #ffa39e;">推送失败</span>';
        showToast('推送失败：ERP接口超时，请稍后重试');
    }
}

function openPushLogModal() {
    document.getElementById('pushLogModal').style.display = 'flex';
}

function closePushLogModal() {
    document.getElementById('pushLogModal').style.display = 'none';
}

function closeAnomalyModal() {
    document.getElementById('anomalyModal').classList.remove('show');
    document.body.style.overflow = '';
}

function openMonitorSettings() {
    document.getElementById('monitorSettingsModal').classList.add('show');
    document.body.style.overflow = 'hidden';
}

function closeMonitorSettings() {
    document.getElementById('monitorSettingsModal').classList.remove('show');
    document.body.style.overflow = '';
}

document.addEventListener('DOMContentLoaded', function() {
    renderTable();
    document.querySelectorAll('input[name="site"]').forEach(radio => {
        radio.closest('label').addEventListener('click', function() {
            document.querySelectorAll('input[name="site"]').forEach(r => r.closest('label').classList.remove('ant-tag-active'));
            this.classList.add('ant-tag-active');
            updateDimensionOptions(this.textContent.trim());
        });
    });
});

const dimensionData = {
    '美国站': [{v:'ST/SS',t:'小号标准尺寸'},{v:'LS',t:'大号标准尺寸'},{v:'SO',t:'小号大件'},{v:'MO',t:'中号大件'},{v:'LO/LB',t:'大号大件'},{v:'SP',t:'特殊大件'},{v:'O',t:'其他尺寸'},{v:'ELO',t:'超大尺寸：0~50磅'},{v:'EL5O',t:'超大尺寸：50~70磅'},{v:'EL7O',t:'超大尺寸：70~150磅'},{v:'EL15O',t:'超大尺寸：150磅以上'}],
    '日本站': [{v:'SM',t:'小号'},{v:'ST',t:'标准'},{v:'OV',t:'大件'},{v:'SS',t:'超大尺寸'},{v:'O',t:'其他尺寸'}],
    '加拿大': [{v:'EN',t:'信封装'},{v:'ST',t:'标准'},{v:'SO',t:'小号大件'},{v:'MO',t:'中号大件'},{v:'LO',t:'大号大件'},{v:'SP',t:'特殊大件'},{v:'O',t:'其他尺寸'}],
    '_eu': [{v:'SL',t:'小号信封'},{v:'NL',t:'标准信封'},{v:'LL',t:'大号信封'},{v:'ELL',t:'超大号信封'},{v:'SM',t:'小包裹'},{v:'SD',t:'标准包裹'},{v:'SB',t:'小号大件'},{v:'NB',t:'标准大件'},{v:'LB',t:'大号大件'},{v:'SPO',t:'特殊大件'},{v:'O',t:'其他尺寸'}]
};

function updateDimensionOptions(site) {
    const sel = document.getElementById('dimensionTypeSelect');
    if (!sel) return;
    const euSites = ['英国站','德国站','法国站','意大利','西班牙'];
    let options = dimensionData[site] || (euSites.includes(site) ? dimensionData['_eu'] : dimensionData['美国站']);
    sel.innerHTML = '<option>不限</option>' + options.map(o => `<option value="${o.v}">${o.t}</option>`).join('');
}

function toggleFilters() {
    const body = document.getElementById('filterBody');
    const icon = document.getElementById('filterToggleIcon');
    const text = document.getElementById('filterToggleText');
    const isHidden = body.style.display === 'none';
    body.style.display = isHidden ? 'grid' : 'none';
    icon.className = isHidden ? 'bi bi-chevron-up' : 'bi bi-chevron-down';
    text.textContent = isHidden ? '隐藏筛选项' : '展开筛选项';
}

document.getElementById('detailModal').addEventListener('click', function(e) {
    if (e.target === this) closeModal();
});

function showReviewPanel(asin) {
    const panel = document.getElementById('reviewAnalysisPanel');
    if (panel) panel.scrollIntoView({behavior: 'smooth', block: 'start'});
}


function openCreateMonitorModal() {
    document.getElementById('createMonitorModal').style.display = 'flex';
}

function closeCreateMonitorModal() {
    document.getElementById('createMonitorModal').style.display = 'none';
}

function openInsightModal(asin) {
    document.getElementById('insightModal').style.display = 'flex';
}

function closeInsightModal() {
    document.getElementById('insightModal').style.display = 'none';
}

function hasAnyFilter() {
    const inputs = document.querySelectorAll('#filterBody input[type="number"], #filterBody input[type="text"], #filterBody .ant-input');
    for (const input of inputs) { if (input.value.trim()) return true; }
    const selects = document.querySelectorAll('#filterBody select');
    for (const sel of selects) { if (sel.selectedIndex > 0) return true; }
    const checkboxes = document.querySelectorAll('#filterBody input[type="checkbox"]:checked');
    if (checkboxes.length > 0) return true;
    return false;
}

function getActivePreset() {
    return document.querySelector('.ant-tag.ant-tag-active');
}

function openPresetModal(mode) {
    if (mode === 'save') {
        const activePreset = getActivePreset();
        const hasFilter = hasAnyFilter();
        if (!activePreset && !hasFilter) {
            showToast('尚未选择任何筛选项目，不可保存');
            return;
        }
        if (activePreset && hasFilter) {
            showToast('方案「' + activePreset.textContent.trim() + '」已保存', 'success');
            return;
        }
    }
    const modal = document.getElementById('presetModal');
    const title = document.getElementById('presetModalTitle');
    if (mode === 'save') {
        title.innerHTML = '<i class="bi bi-save" style="color:var(--primary);"></i> 保存当前筛选为方案';
    } else {
        title.innerHTML = '<i class="bi bi-bookmark-star" style="color:var(--primary);"></i> 新建筛选方案';
    }
    modal.classList.add('show');
    document.body.style.overflow = 'hidden';
}

function showToast(msg, type) {
    const existing = document.querySelector('.toast-msg');
    if (existing) existing.remove();
    const el = document.createElement('div');
    el.className = 'toast-msg';
    const bgColor = type === 'success' ? '#f6ffed' : '#fff2f0';
    const borderColor = type === 'success' ? '#b7eb8f' : '#ffa39e';
    const textColor = type === 'success' ? '#389e0d' : '#cf1322';
    const icon = type === 'success' ? 'bi-check-circle-fill' : 'bi-exclamation-circle-fill';
    el.style.cssText = 'position:fixed;top:60px;left:50%;transform:translateX(-50%);z-index:9999;padding:10px 20px;border-radius:6px;font-size:13px;box-shadow:0 4px 12px rgba(0,0,0,.1);background:' + bgColor + ';border:1px solid ' + borderColor + ';color:' + textColor + ';display:flex;align-items:center;gap:8px;';
    el.innerHTML = '<i class="bi ' + icon + '"></i> ' + msg;
    document.body.appendChild(el);
    setTimeout(() => el.remove(), 2500);
}

function closePresetModal() {
    document.getElementById('presetModal').classList.remove('show');
    document.body.style.overflow = '';
}

function savePreset() {
    const name = document.getElementById('presetName').value.trim();
    if (!name) { alert('请输入方案名称'); return; }
    const visibility = document.querySelector('input[name="presetVisibility"]:checked').value;
    const labels = { private: '我的', team: '组共享', global: '系统' };
    const tagContainer = document.querySelector('.ant-card-body');
    const tag = document.createElement('span');
    tag.className = 'ant-tag';
    tag.onclick = function() { selectPreset(this); };
    tag.textContent = name + '（' + labels[visibility] + '）';
    tagContainer.appendChild(tag);
    closePresetModal();
    document.getElementById('presetName').value = '';
    document.getElementById('presetDesc').value = '';
}
