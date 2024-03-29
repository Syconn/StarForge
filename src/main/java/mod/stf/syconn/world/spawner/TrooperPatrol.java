package mod.stf.syconn.world.spawner;

import mod.stf.syconn.common.entity.StormTrooper;
import mod.stf.syconn.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class TrooperPatrol implements CustomSpawner {
    private int nextTick;

    public int tick(ServerLevel pLevel, boolean pSpawnEnemies, boolean pSpawnFriendlies) {
        if (!pSpawnEnemies) {
            return 0;
        } else {
            RandomSource random = pLevel.random;
            --this.nextTick;
            if (this.nextTick > 0) {
                return 0;
            } else {
                this.nextTick += 800;
                if (pLevel.isDay()) {
                    if (random.nextInt(2) != 0) {
                        return 0;
                    }

                    else {
                        int j = pLevel.players().size();
                        if (j < 1) {
                            return 0;
                        }

                        else {
                            Player player = pLevel.players().get(random.nextInt(j));
                            if (player.isSpectator()) {
                                return 0;
                            } else {
                                int k = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
                                int l = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
                                BlockPos.MutableBlockPos blockpos$mutableblockpos = player.blockPosition().mutable().move(k, 0, l);
                                if (!pLevel.hasChunksAt(blockpos$mutableblockpos.getX() - 10, blockpos$mutableblockpos.getZ() - 10, blockpos$mutableblockpos.getX() + 10, blockpos$mutableblockpos.getZ() + 10)) {
                                    return 0;
                                } else {
                                    if (pLevel.getBiome(blockpos$mutableblockpos).is(Tags.Biomes.IS_MUSHROOM)) {
                                        return 0;
                                    } else {
                                        int j1 = 0;
                                        int k1 = (int)Math.ceil(pLevel.getCurrentDifficultyAt(blockpos$mutableblockpos).getEffectiveDifficulty()) + 3;
                                        for(int l1 = 0; l1 < k1; ++l1) {
                                            ++j1;
                                            blockpos$mutableblockpos.setY(pLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos$mutableblockpos).getY());
                                            if (l1 == 0) {
                                                System.out.println("Spawning");
                                                if (!this.spawnPatrolMember(pLevel, blockpos$mutableblockpos, random, true)) {
                                                    break;
                                                }
                                            } else {
                                                this.spawnPatrolMember(pLevel, blockpos$mutableblockpos, random, false);
                                            }
                                            blockpos$mutableblockpos.setX(blockpos$mutableblockpos.getX() + random.nextInt(5) - random.nextInt(5));
                                            blockpos$mutableblockpos.setZ(blockpos$mutableblockpos.getZ() + random.nextInt(5) - random.nextInt(5));
                                        }
                                        return j1;
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    return 0;
                }
            }
        }
    }

    private boolean spawnPatrolMember(ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, boolean pLeader) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        if (!NaturalSpawner.isValidEmptySpawnBlock(pLevel, pPos, blockstate, blockstate.getFluidState(), ModEntities.STORMTROOPER.get())) {
            return false;
        } else {
            StormTrooper stormTrooper = ModEntities.STORMTROOPER.get().create(pLevel);
            if (stormTrooper != null) {
                if (pLeader) {
                    stormTrooper.setLeader(true);
                    stormTrooper.findPatrolTarget();
                }

                else {
                    stormTrooper.setLeader(false);
                }

                stormTrooper.setPos((double)pPos.getX(), (double)pPos.getY(), (double)pPos.getZ());
                stormTrooper.finalizeSpawn(pLevel, pLevel.getCurrentDifficultyAt(pPos), MobSpawnType.PATROL, (SpawnGroupData)null, (CompoundTag)null);
                pLevel.addFreshEntityWithPassengers(stormTrooper);
                return true;
            } else {
                return false;
            }
        }
    }
}
