# Mwongozo wa Kuweka LOIE EXPRESSES Mtandaoni (Render) — Kiswahili

Mwongozo huu ni click-kwa-click. Fuata hatua moja baada ya nyingine.
Utahitaji akaunti mbili (zote bure): **GitHub** na **Render**.

---

## SEHEMU YA 1 — Tengeneza akaunti ya GitHub (ruka kama unayo)

1. Fungua **https://github.com** kwenye browser.
2. Bonyeza **Sign up**.
3. Weka email yako, tengeneza password, chagua username (mfano: `davis-tz`).
4. Thibitisha email yako (GitHub itakutumia barua pepe ya kuthibitisha).

---

## SEHEMU YA 2 — Pakia code yako GitHub

Njia rahisi kabisa (bila kutumia commands):

1. Ukiwa umeingia GitHub, bonyeza alama ya **+** juu kulia → **New repository**.
2. **Repository name**: andika `loie-expresses`.
3. Chagua **Private** (au Public, chaguo lako).
4. Bonyeza **Create repository**.
5. Kwenye ukurasa unaofuata, bonyeza kiungo **uploading an existing file**
   (kinaonekana katikati ya ukurasa).
6. **Fungua** faili la `loie-expresses.zip` kwenye kompyuta yako kwanza
   (bonyeza mara mbili ili li-extract / lifunguke kuwa folda).
7. Buruta (drag) **vitu vyote vilivyomo ndani ya folda** ya loie-expresses
   uviweke kwenye ukurasa wa GitHub. (Siyo zip yenyewe — bali faili zilizomo:
   `src`, `pom.xml`, `Dockerfile`, `render.yaml`, n.k.)
8. Chini, bonyeza **Commit changes**.

> Faili muhimu lazima zionekane GitHub: `pom.xml`, `Dockerfile`, `render.yaml`, na folda `src`.

---

## SEHEMU YA 3 — Tengeneza akaunti ya Render

1. Fungua **https://render.com**.
2. Bonyeza **Get Started** au **Sign In**.
3. Chagua **GitHub** (bonyeza "Sign in with GitHub") — hii inaunganisha akaunti zako.
4. Render itaomba ruhusa kusoma repositories zako — bonyeza **Authorize**.

---

## SEHEMU YA 4 — Weka website + database kwa mpigo mmoja (Blueprint)

Kwa sababu tumeweka faili la `render.yaml`, Render inaweza kuunda **website NA
database** pamoja moja kwa moja.

1. Ukiwa kwenye dashboard ya Render, bonyeza **New +** (juu kulia) → **Blueprint**.
2. Chagua repository yako ya **loie-expresses**. Bonyeza **Connect**.
3. Render itasoma `render.yaml` na kukuonyesha:
   - **loie-expresses** (web service)
   - **loie-db** (PostgreSQL database)
4. Bonyeza **Apply** (au **Create Services**).
5. Subiri. Render sasa:
   - Inaunda database,
   - Inajenga website kwa Docker (dakika 3–7 mara ya kwanza),
   - Inaunganisha database na website yenyewe (huna cha kufanya).

> Ukiona neno **"Live"** kwa rangi ya kijani kwenye web service — website iko tayari!

---

## SEHEMU YA 5 — Fungua website yako

1. Bonyeza jina **loie-expresses** kwenye dashboard.
2. Juu utaona anwani kama: **https://loie-expresses.onrender.com**
3. Bonyeza — website yako itafunguka!
4. Ili kuingia admin: ongeza `/login` mwishoni:
   `https://loie-expresses.onrender.com/login`
   - Username: **admin**
   - Password: **admin123**
   - **Badilisha password mara moja** ukiingia (Users → Edit).

---

## MAMBO YA KUFAHAMU (muhimu)

**1. "Kulala" kwa plan ya bure**
Website ikikaa dakika 15 bila mtumiaji, inalala. Mtu wa kwanza baadaye atasubiri
kama sekunde 50 ndipo ifunguke. Baada ya hapo ni haraka. Ili kuondoa hili kabisa,
badilisha web service kuwa plan ya **Starter ($7/mwezi)**.

**2. Database ya bure ya Render huisha baada ya siku 30**
Render itakutumia barua pepe. Utahitaji kuboresha (upgrade) database au kuihamishia
kwingine. Kwa biashara halisi, plan ndogo ya database inayolipiwa inashauriwa.

**3. Picha zinazopakiwa (uploads)**
Kwenye plan ya bure, picha ulizopakia zinaweza kufutika app inapojijenga upya.
Kwa kudumu, baadaye tunaweza kuunganisha "disk" ya Render au hifadhi ya wingu (S3).

**4. Kuonekana Google (njia rahisi — hakuna kugusa code)**

Website tayari ina uwezo wa kuthibitishwa Google kupitia **environment variable**,
sitemap.xml, na robots.txt. Fuata hivi:

*Hatua A — Pata code ya Google*
1. Fungua **https://search.google.com/search-console**
2. Ingia na Gmail yako.
3. Bonyeza **Add property** → chagua **URL prefix** (upande wa kulia).
4. Weka URL yako kamili ya Render, mfano: `https://loie-expresses.onrender.com`
5. Bonyeza **Continue**.
6. Chagua njia ya **HTML tag**. Google itakuonyesha kitu kama:
   `<meta name="google-site-verification" content="ABC123..." />`
7. **Nakili sehemu ya `content` tu** — ile `ABC123...` (bila `<meta...>`).

*Hatua B — Weka code kwenye Render (siyo kwenye code!)*
1. Kwenye Render, fungua service yako ya **loie-expresses**.
2. Nenda tab ya **Environment**.
3. Bonyeza **Add Environment Variable**.
4. Key: `GOOGLE_SITE_VERIFICATION`
   Value: bandika ile `ABC123...` uliyonakili.
5. Bonyeza **Save Changes**. Render itajijenga upya (subiri dakika chache).

*Hatua C — Thibitisha*
1. Rudi Google Search Console.
2. Bonyeza **Verify**. Itafanikiwa (green tick).

*Hatua D — Ongeza sitemap (isaidie Google kupata kurasa zote)*
1. Kwanza, kwenye admin dashboard yako (`/admin/settings`), weka **Site URL**
   kuwa URL yako ya Render, bonyeza Save. (Hii inawasha sitemap na canonical.)
2. Kwenye Google Search Console, upande wa kushoto bonyeza **Sitemaps**.
3. Andika: `sitemap.xml` → bonyeza **Submit**.

Ndani ya siku chache hadi wiki 2, ukisearch jina lako Google, website itaonekana —
kama SOMA.

**5. Kila unapobadilisha code**
Ukibadilisha kitu na kupakia tena GitHub (au push), Render itajenga upya yenyewe.

---

## Kama kuna tatizo (build imefeli)

1. Kwenye Render, bonyeza web service → tab ya **Logs**.
2. Nakili (copy) ujumbe wa kosa (mara nyingi wa mwisho, wenye neno ERROR).
3. Nirudishie hapa — nitakusaidia kurekebisha haraka.

Mara nyingi makosa ya kwanza ni: database bado haijaunganishwa (subiri kidogo), au
faili moja halikupakiwa GitHub. Logs zitakuonyesha.

---

Umefanya vizuri! Ukikwama hatua yoyote, niambie namba ya hatua (mfano "Sehemu 4,
hatua 3") na nitakuelekeza.
