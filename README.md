# MVML Template
### Multi-Version Multi-Loader Template

A template for development on multiple versions and loaders.
All at the same time, all in one file!

## About template
### Dependencies
Fabric API is **not** included by default,
however it's implemented with `runtimeOnly` for modmenu which is
included by default of fabric. If you want to use it in your development
check out lines `112` & `114` of `build.gradle`.

### Versions
In `settings.gradle` all versions are set, you can easily comment them out or even add your own!
Here's a table of versions supported by default:

|  Version   | Fabric/Quilt | Forge | NeoForge |
|:----------:|:------------:|:-----:|:--------:|
|    26.2    |      ✅       |   ❌   |    ✅     |
| 26.1(.1/2) |      ✅       |   ❌   |    ✅     |
|  1.21.11   |      ✅       |   ❌   |    ✅     |
| 1.21.9/10  |      ✅       |   ❌   |    ✅     |
|  1.21.2-8  |      ✅       |   ❌   |    ✅     |
|  1.21(.1)  |      ✅       |   ❌   |    ✅     |
|  1.20.5/6  |      ✅       |   ❌   |    ✅     |
|  1.20.2-4  |      ✅       |   ❌   |    ✅     |
|  1.20(.1)  |      ✅       |   ✅   |    ❌     |
|   1.19.2   |      ✅       |   ✅   |    ❌     |
|   1.18.2   |      ❌       |   ✅   |    ❌     |

### copybuilds.sh
There's also a `copybuilds.sh`, a script to easily copy all built JARs into one place, 
`publish-builds` folder

## Checklist
Here's a checklist of things to do with this template
before starting the development. Don't worry, it's small!

- [ ] Edit `gradle.properties`
- [ ] Rename `mod_id.mixins.json`
- [ ] Add `icon.png` to `resources` folder
- [ ] Put `MainClass` into your package and rename it
- [ ] Run `Refresh active project` from `Tasks/stonecutter`
- [ ] Set maven publish settings in the end of `build.gradle` (optional)
- [ ] Delete `CHANGELOG.md` & `README.md`

Happy development!