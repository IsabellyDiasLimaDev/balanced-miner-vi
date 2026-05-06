# Balanced Miner VI

**Balanced Miner VI** adiciona o encantamento **Eficiência VI** para picaretas no Minecraft, com foco em mineração rápida, prática e controlada.

O objetivo do mod é simples: deixar a mineração de pedras e minérios muito mais fluida, sem transformar blocos sensíveis como netherrack, melancia, abóbora e quartzo do Nether em uma bagunça de instamine indesejado.

## Recursos

- Adiciona o encantamento **Eficiência VI**
- Compatível apenas com **picaretas**
- Funciona com o sistema de encantamentos data-driven do Minecraft 1.21+
- Pode aparecer em livros de bibliotecário
- Pode aparecer na mesa de encantamentos
- Acelera apenas blocos permitidos por tag própria
- Protege blocos sensíveis contra mineração instantânea indevida
- Inclui traduções em inglês e português brasileiro

## Versões Suportadas

- Minecraft: **1.21.11**
- NeoForge: **21.11.42**
- Java: **21**

## Como Funciona

O encantamento **Eficiência VI** não aplica um bônus global sem controle.

Em vez disso, o mod escuta o evento de velocidade de mineração e só acelera a quebra quando todas estas condições são verdadeiras:

1. O jogador está usando uma picareta.
2. A picareta tem **Eficiência VI**.
3. O bloco está na tag `balanced_miner_vi:mineable_standard`.
4. O bloco também é minerável com picareta.
5. O bloco não está na blacklist de instamine.
6. O bloco não tem dureza muito baixa.

Isso permite que pedras e minérios sejam minerados muito rápido, mantendo blocos perigosos fora do efeito.

## Blocos Acelerados

Por padrão, o mod acelera blocos comuns de mineração, incluindo:

- Stone
- Cobblestone
- Mossy Cobblestone
- Deepslate
- Cobbled Deepslate
- Andesite
- Diorite
- Granite
- Tuff
- Calcite
- Basalt
- Blackstone
- Reinforced Deepslate
- Coal Ore
- Iron Ore
- Copper Ore
- Gold Ore
- Redstone Ore
- Lapis Lazuli Ore
- Diamond Ore
- Emerald Ore
- Nether Gold Ore
- Nether Quartz Ore
- Versões deepslate dos minérios

## Blocos Protegidos

O mod evita alterar blocos que podem causar instamine indesejado, como:

- Netherrack
- Melancia
- Abóbora
- Quartz Block
- Chiseled Quartz Block
- Quartz Pillar
- Quartz Bricks
- Smooth Quartz
- Blocos com dureza muito baixa

## Instalação

1. Instale o Minecraft **1.21.11** com **NeoForge 21.11.42**.
2. Baixe o arquivo `.jar` do mod.
3. Coloque o `.jar` na pasta `mods`.
4. Abra o jogo usando o perfil NeoForge.

No Windows, a pasta padrão costuma ser:

```text
%APPDATA%\.minecraft\mods
```

No TLauncher, coloque o `.jar` na pasta `mods` do perfil NeoForge usado pelo jogo.

## Build Para Desenvolvedores

Clone o projeto e rode:

```powershell
./gradlew.bat build
```

O arquivo final será gerado em:

```text
build/libs/balanced_miner_vi-1.0-SNAPSHOT.jar
```

## Personalização Por Tag

O mod usa esta tag para decidir quais blocos podem receber a velocidade especial:

```text
data/balanced_miner_vi/tags/block/mineable_standard.json
```

Se você quiser criar um datapack ou fork personalizado, basta alterar essa lista. Blocos fora dela não recebem o efeito da Eficiência VI.

## Traduções

Português brasileiro:

```json
"enchantment.balanced_miner_vi.efficiency_vi": "Eficiência VI"
```

Inglês:

```json
"enchantment.balanced_miner_vi.efficiency_vi": "Efficiency VI"
```

## Por Que Usar?

Se você gosta de mineração rápida, mas não quer quebrar totalmente o equilíbrio do jogo, **Balanced Miner VI** é feito para isso.

Ele deixa a mineração de pedra e minérios muito mais satisfatória, sem mexer em blocos fora da proposta do mod.

## Licença

All Rights Reserved.

